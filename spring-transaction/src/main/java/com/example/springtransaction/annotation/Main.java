package com.example.springtransaction.annotation;


import com.example.springtransaction.annotation.service.LoginService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(MainConfig.class);
        LoginService loginService = ac.getBean("loginServiceImpl", LoginService.class);
        loginService.login(1);
    }
}
