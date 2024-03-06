package com.example.springaop.proxy;

public class Main {
    public static void main(String[] args) throws Exception {
        NameService nameService = new DynamicProxy<NameService>(new NameServiceImpl()).getProxy();
        nameService.printName("tiang");
        nameService.test();
    }
}