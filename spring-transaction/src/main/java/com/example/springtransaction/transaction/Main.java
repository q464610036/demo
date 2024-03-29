package com.example.springtransaction.transaction;


import com.example.springtransaction.transaction.service.LoginService;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(MainConfig.class);
        LoginService loginService = ac.getBean("loginServiceImpl", LoginService.class);
        ComboPooledDataSource dataSource = (ComboPooledDataSource)ac.getBean("dataSource");
        loginService.login(1);
    }
}
