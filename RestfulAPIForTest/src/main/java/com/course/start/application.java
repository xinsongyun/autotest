package com.course.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.course")
public class application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(application.class, args);
    }
}
