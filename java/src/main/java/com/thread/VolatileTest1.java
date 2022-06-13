package com.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 多个线程与一个服务建立连接，但是只要一个线程建立连接就行了，其他线程不重新建立连接
 * volatile用户状态一致性，第一个线程设置为true其他线程立马就能知道
 * synchronize用于原子性，因为在第一个线程将flag设置为true之前，有的线程代码可能已经走到if后面了
 * @date 2021/9/28
 */
public class VolatileTest1 {
    public static volatile Boolean flag = false;

    public void connection(){
        if (flag) {
            System.out.println("重复建立连接异常");
           throw new  RuntimeException("重复建立连接异常");
        }
        System.out.println("建立连接");
        flag = true;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService ExecutorService = Executors.newFixedThreadPool(10);
        VolatileTest1 t1 = new VolatileTest1();
        for (int i=0;i<100; i++) {
            ExecutorService.submit(new Runnable() {
                @Override
                public void run() {
                    t1.connection();
                }
            });
        }
        Thread.sleep(10000);
        ExecutorService.shutdown();
    }
}
