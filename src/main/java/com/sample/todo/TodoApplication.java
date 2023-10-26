package com.sample.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan(value = "com.sample.todo.**.repository")
@SpringBootApplication
public class TodoApplication {

    public static void main( String[] args ) {
        SpringApplication.run( TodoApplication.class, args );
    }

}
