package com.example.springtransaction.transaction;


import com.example.springtransaction.transaction.service.LoginService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(MainConfig.class);
        LoginService loginService = ac.getBean("loginServiceImpl", LoginService.class);
        loginService.login(1);
    }
}
