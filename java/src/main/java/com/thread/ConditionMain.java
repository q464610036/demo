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
    public static void main(String[] args) throws Exception {
        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition(); //生产者条件
        Condition condition2 = lock.newCondition(); //消费者条件
        CountDownLatch countDownLatch = new CountDownLatch(1);
        t1 = new Thread(() -> {
            try {
                countDownLatch.countDown(); //打开倒计时门栓，表示生产者执行过了，不用再拦截消费者了
                while (true) {
                    lock.lock(); //进来先加锁，防止别的线程进来
                    System.out.println(Thread.currentThread().getName()+":我生产好了");
                    Thread.sleep(1000);
                    condition2.signal(); //condition2发信号，哪些线程正在用condition2 wait，这些线程就不需要wait了。
                    condition1.await(); //condition1 wait，表示生产者线程正在用condition1 wait，只有当condition1变成signal才可唤醒。
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1");
        t2 = new Thread(() -> {
            try {
                countDownLatch.await(); //倒计时门栓，门栓没被打开过则不准运行
                while (true) {
                    lock.lock(); //进来先加锁，防止别的线程进来
                    System.out.println(Thread.currentThread().getName()+":我消费好了");
                    Thread.sleep(1000);
                    condition1.signal(); //condition1发信号，哪些线程正在用condition1 wait，这些线程就不需要wait了。
                    condition2.await(); //condition2 wait，表示生产者线程正在用condition2 wait，只有当condition2变成signal才可唤醒。
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2");

        t2.start();
        Thread.sleep(1000);
        t1.start();


    }
}
