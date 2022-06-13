package com.servletdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 陈孟飞
 * @date 2021/8/30
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getContextClassLoader().toString());
        SpringApplication.run(Application.class,args);
    }
}
