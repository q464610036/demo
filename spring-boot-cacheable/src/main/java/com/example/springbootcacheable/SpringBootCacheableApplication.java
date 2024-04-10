package com.example.springbootcacheable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.SpringVersion;

@SpringBootApplication
@EnableCaching
public class SpringBootCacheableApplication {

    public static void main(String[] args) {
        String sVersion = SpringVersion.getVersion();
        System.out.println("Spring 版本号：" + sVersion);
        String bVersion = SpringBootVersion.getVersion();
        System.out.println("SpringBoot 版本号：" + bVersion);
        SpringApplication.run(SpringBootCacheableApplication.class, args);
    }

}