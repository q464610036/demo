package com.example.springaop.proxy;

public class NameServiceImpl implements NameService {
    @Override
    public void printName(String name) {
        System.out.println(name);
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void test() {
        System.out.println("test");
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
