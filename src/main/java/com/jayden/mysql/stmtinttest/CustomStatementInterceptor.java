package com.jayden.mysql.stmtinttest;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSetInternalMethods;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.StatementInterceptorV2;
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
        logger.info("init conn, props: {}", props);
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
        logger.info("count {}", originalResultSet.getUpdateCount());
        return null;
    }
}
