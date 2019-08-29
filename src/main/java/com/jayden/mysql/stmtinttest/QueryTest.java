package com.jayden.mysql.stmtinttest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

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

    public void query() {
        System.out.println(jdbcTemplate.queryForList("select USERNAME from ts_user"));
    }
}
