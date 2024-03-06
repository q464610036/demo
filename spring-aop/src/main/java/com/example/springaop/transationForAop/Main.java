package com.example.springaop.transationForAop;


import com.example.springaop.transationForAop.service.LoginService;
import com.example.springaop.transationForAop.service.impl.LoginServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(MainConfig.class);
        LoginService loginService = ac.getBean("loginServiceImpl", LoginService.class);
        loginService.login(1);
    }

}
