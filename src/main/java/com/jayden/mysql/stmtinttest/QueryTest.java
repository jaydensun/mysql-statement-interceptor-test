package com.jayden.mysql.stmtinttest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author sunyongjun
 * @since 2019/8/29
 */
@Component
public class QueryTest {
    private final JdbcTemplate jdbcTemplate;

    public QueryTest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void test() {
        // select
        System.out.println(jdbcTemplate.queryForList("select id from ts_node_partition").get(0));

        System.out.println(jdbcTemplate.query(connection -> {
            return connection.prepareStatement("select id from ts_node_partition");
        }, preparedStatement -> {
        }, this::fetchCount) + "");

        System.out.println(jdbcTemplate.query(connection -> {
            return connection.prepareStatement("select id from ts_node_partition where id > ? and node_id > ? limit 7");
        }, preparedStatement -> {
            preparedStatement.setInt(1, 100);
            preparedStatement.setInt(2, 10);
        }, this::fetchCount) + "");

        printTest();

        // insert
        jdbcTemplate.update("insert into test(id) values (1)");
        jdbcTemplate.update("insert into test(id) values (2), (3)");
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement("insert into test(id) values (?)");
            preparedStatement.setInt(1, 4);
            return preparedStatement;
        });
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement("insert into test(id) values (?),(?),(?)");
            preparedStatement.setInt(1, 5);
            preparedStatement.setInt(2, 6);
            preparedStatement.setInt(3, 7);
            return preparedStatement;
        });
        printTest();

        // update
        jdbcTemplate.update("update test set value = 1 where id < 3");
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement("update test set value = 2 where id >= ?");
            preparedStatement.setInt(1, 3);
            return preparedStatement;
        });
        printTest();

        // delete
        jdbcTemplate.update("delete from test where id < 3");
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement("delete from test where id >= ?");
            preparedStatement.setInt(1, 3);
            return preparedStatement;
        });
        printTest();
    }

    private void printTest() {
        System.out.println(jdbcTemplate.queryForList("select * from test"));
    }

    private Integer fetchCount(ResultSet resultSet) throws SQLException {
        int count = 0;
        while (resultSet.next()) {
            count++;
        }
        return count;
    }
}
