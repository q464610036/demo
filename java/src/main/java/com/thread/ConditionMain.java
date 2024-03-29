package com.thread;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者线程交替执行
 * @author 陈孟飞
 * @date 2021/8/6
 */
public class ConditionMain {
    static Thread t1;
    static Thread t2;
    static Thread t3;
    public static void main(String[] args) throws Exception {
        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        t1 = new Thread(() -> {
            try {
                while (true) {
                    lock.lock();
                    countDownLatch.countDown();
                    System.out.println(Thread.currentThread().getName()+":我生产好了");
                    condition2.signal();
                        condition1.await();

                    lock.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1");
        t2 = new Thread(() -> {
            try {
                countDownLatch.await();
                while (true) {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName()+":我消费好了");
                    condition1.signal();
                    condition2.await();
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2");
        t3 = new Thread(() -> {
            try {
                countDownLatch.await();
                while (true) {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName()+":我消费好了");
                    condition1.signal();
                    condition2.await();
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t3");
        t1.start();
        t2.start();
        t3.start();

    }
}
