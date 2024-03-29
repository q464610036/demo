package com.lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class MyLockMain {
    public static void main(String[] args) {
        MyLock myLock = new MyLock();
        Thread t1 = new Thread(() -> {
            myLock.lock();
            try {
                System.out.println("现成1正在干活");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myLock.unlock();
        }, "t1");

        Thread t2 = new Thread(() -> {
            myLock.lock();
            try {
                System.out.println("现成2正在干活");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myLock.unlock();
        }, "t2");
        t1.start();
        t2.start();
    }
}
