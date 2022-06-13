package com.thread;

import java.util.concurrent.*;

/**
 * @author 陈孟飞
 * @date 2021/8/9
 */
public class ThreadPoolMain {
    public static void main(String[] args) {
        singleThreadExecutor();
    }

    //单个线程池，只有一个线程的线程池
    public static void singleThreadExecutor() {
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });
        es.shutdown();
    }

    //固定线程池，固定3个线程执行
    public static void fixedThreadPool() {
        ExecutorService es = Executors.newFixedThreadPool(3);
        es.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });
        es.shutdown();
    }

    //缓存线程池，线程数无线，如果线程空闲60s则会回收线程，如果线程数不够则会新增线程。
    public static void cachedThreadPool() {
        ExecutorService es = Executors.newCachedThreadPool();
        es.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });
        es.shutdown();
    }

    //定长线程池，5个线程，延迟3秒执行
    public static void scheduledThreadPool1() {
        ScheduledExecutorService es = Executors.newScheduledThreadPool(5);
        es.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }, 3, TimeUnit.SECONDS);
        es.shutdown();
    }

    //5个线程，延迟3秒执行
    public static void scheduledThreadPool2() {
        ScheduledExecutorService es = Executors.newScheduledThreadPool(5);
        es.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }, 1, 3, TimeUnit.SECONDS);
        es.shutdown();
    }
}
