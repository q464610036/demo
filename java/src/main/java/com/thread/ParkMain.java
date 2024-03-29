package com.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * @author 陈孟飞
 * @date 2021/8/6
 */
public class ParkMain {
    static Thread t1;
    static Thread t2;
    static Thread t3 ;
    public static void main(String[] args) throws InterruptedException {
        t1 = new Thread(() -> {
            while (true) {
                System.out.println("thread1");
                LockSupport.unpark(t2);
                LockSupport.park();
            }

        },"t1");
        t2 = new Thread(() -> {
            while (true) {
                LockSupport.park();
                System.out.println("thread2");
                LockSupport.unpark(t1);
            }
        },"t2");
        t1.start();
        t2.start();
        t3.start();

    }
}
