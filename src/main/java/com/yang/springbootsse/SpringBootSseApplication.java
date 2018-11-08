package com.yang.springbootsse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SpringBootSseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSseApplication.class, args);
    }
}
