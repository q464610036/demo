package com.reflex;

/**
 * @author 陈孟飞
 * @date 2021/8/25
 */
public class User {
    private User(){
        System.out.println("create user");
    }

    private User(String name){
        System.out.println(name);
    }

    public void test1(){
        System.out.println("test1");
    }

    private void test2(){
        System.out.println("test2");
    }
}
