package com.example.springaop.transation;

public class TransactionManager {
    public static void begin(){
        System.out.println("开启事务");
    }

    public static void commit(){
        System.out.println("提交事务");
    }

    public static void rollback(){
        System.out.println("回滚事务");
    }

    public static void release(){
        System.out.println("关闭连接");
    }
}
