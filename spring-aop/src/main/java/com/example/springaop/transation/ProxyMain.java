package com.example.springaop.transation;


public class ProxyMain {
    public static void main(String[] args) {
        LoginService loginService = new TransactionManagerProxy<LoginService>(new LoginServiceImpl()).getProxy();
        loginService.login();
    }

}
