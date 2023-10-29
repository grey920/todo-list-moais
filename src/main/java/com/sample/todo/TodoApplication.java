package com.sample.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;

@MapperScan(value = "com.sample.todo.**.repository")
@ComponentScan(value = "com.sample.todo.**")
@SpringBootApplication
public class TodoApplication {

    public static void main( String[] args ) {
        SpringApplication.run( TodoApplication.class, args );
    }

}
