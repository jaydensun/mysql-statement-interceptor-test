package com.jayden.mysql.stmtinttest;

import com.mysql.jdbc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.Properties;

/**
 * @author sunyongjun
 * @since 2019/8/29
 */
public class CustomStatementInterceptor implements StatementInterceptorV2 {
    private static Logger logger = LoggerFactory.getLogger(CustomStatementInterceptor.class);

    public void init(Connection conn, Properties props) throws SQLException {
    }

    public ResultSetInternalMethods preProcess(String sql, Statement interceptedStatement, Connection connection) throws SQLException {
        return null;
    }

    public boolean executeTopLevelOnly() {
        return false;
    }

    public void destroy() {

    }

    public ResultSetInternalMethods postProcess(String sql, Statement interceptedStatement, ResultSetInternalMethods originalResultSet, Connection connection, int warningCount, boolean noIndexUsed, boolean noGoodIndexUsed, SQLException statementException) throws SQLException {
        String actualSql = sql;
        if (actualSql == null) {
            if (interceptedStatement instanceof PreparedStatement) {
                PreparedStatement statement = (PreparedStatement) interceptedStatement;
                actualSql = statement.asSql();
            }
        }
        String lowerSql = actualSql == null ? "" : actualSql.toLowerCase().trim();
        if ((lowerSql.startsWith("insert") && lowerSql.contains("into"))
                || (lowerSql.startsWith("delete") && lowerSql.contains("from"))
                || (lowerSql.startsWith("update") && lowerSql.contains("set"))
                || (lowerSql.startsWith("select") && lowerSql.contains("from"))) {
            long updateCount = originalResultSet.getUpdateCount();
            logger.info("updateCount: {}, sql: {}", updateCount, lowerSql);
        }
        return null;
    }
}
