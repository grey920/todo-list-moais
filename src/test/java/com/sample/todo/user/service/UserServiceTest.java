package com.sample.todo.user.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

@Slf4j
@SpringBootTest
@ExtendWith( SpringExtension.class)
class UserServiceTest {

    @Autowired private UserService userService;

    @Test
    public void init() {
        log.info("init");
    }

    @Test
    @DisplayName( "회원 전체 조회" )
    public void listAllUser() {
        userService.listAllUser().forEach( user -> log.info("user: {}", user));
    }

}