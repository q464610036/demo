package com.thread;

/**
 * @author 陈孟飞
 * @date 2021/8/6
 */
public class VolatileTest2 {
    public static volatile boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while(flag){
                System.out.println("thread1");
                try {
                    System.out.println("flag:"+flag);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(() -> {
            flag = false;
            System.out.println("flag = true");
        });
        t1.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}
