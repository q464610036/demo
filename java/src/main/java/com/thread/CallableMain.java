package com.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author 陈孟飞
 * @date 2021/8/6
 */
public class CallableMain {
    public static void main(String[] args) {
//        test1();
        test2();
    }

    public static void test1() {
        Callable1 c1 = new Callable1();
        try {
            String str = c1.call();
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test2() {
        FutureTask ft = new FutureTask(new Callable1());
        new Thread(ft).start();
        try {
            Object obj = ft.get();
            String str = String.valueOf(obj);
            System.out.println(str);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
