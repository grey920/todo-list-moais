package com.sample.todo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@Slf4j
@Component
@RequiredArgsConstructor
public class TestRunner implements ApplicationRunner {

    private final DataSource dataSource;

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void run( ApplicationArguments args ) throws Exception {

        // DataSource 연결 확인
        Connection connection = dataSource.getConnection();
        log.info("Url: " + connection.getMetaData().getURL());
        log.info("UserName: " + connection.getMetaData().getUserName());

    }
}
