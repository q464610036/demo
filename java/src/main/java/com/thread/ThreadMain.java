package com.thread;

/**
 * @author 陈孟飞
 * @date 2021/8/6
 */
public class ThreadMain {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println("thread1");
        });
        Thread t2 = new Thread(() -> {
            System.out.println("thread2");
        });
        Thread t3 = new Thread(() -> {
            System.out.println("thread3");
        });
        t1.start();
        t1.join();
        t2.start();
        t2.join();
        t3.start();
        t3.join();
    }
}
