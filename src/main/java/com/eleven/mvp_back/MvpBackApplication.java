package com.eleven.mvp_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MvpBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(MvpBackApplication.class, args);
    }

}
