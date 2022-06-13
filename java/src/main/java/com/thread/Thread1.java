package com.thread;

/**
 * @author 陈孟飞
 * @date 2021/8/6
 */
public class Thread1 extends Thread {
    @Override
    public void run() {
        for (; ; ) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
