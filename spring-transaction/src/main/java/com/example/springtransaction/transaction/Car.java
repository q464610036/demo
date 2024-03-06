package com.example.springtransaction.transaction;

public class Car {
    public void init(){
        System.out.println("car-init-running");
    }

    public void destroy(){
        System.out.println("car-init-destory");
    }
}
