package com.synchronized1;

/**
 * @author 陈孟飞
 * @date 2021/9/28
 */
public class SynchronizedTest1 {

    public static void main(String[] args) {
        synchronized (SynchronizedTest1.class) {
            int a = 1;
        }
    }
}
