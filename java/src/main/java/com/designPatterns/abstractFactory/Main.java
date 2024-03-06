package com.designPatterns.abstractFactory;

import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;

public class Main {
    public static void main(String[] args) {
        SchemeB b = new SchemeB();
        CustomerService customerService = new CustomerService(b);
        customerService.buyCar(1, "辆");
        customerService.offerFruit(10, "斤");
    }
}
