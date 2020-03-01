package com.mini.store.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//https://stackoverflow.com/questions/56249159/use-bcrypt-hashing-function-in-spring-boot-without-all-the-overkill-security
@SpringBootApplication(scanBasePackages = {"com.mini.store.demo"}, exclude = { SecurityAutoConfiguration.class })
@MapperScan("com.mini.store.demo.dao")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
