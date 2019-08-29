package com.jayden.mysql.stmtinttest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author sunyongjun
 * @since 2019/8/29
 */
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        QueryTest queryTest = context.getBean(QueryTest.class);
        queryTest.query();
    }
}
