package com.atomicinteger;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 陈孟飞
 * @date 2021/7/22
 */
public class AtomicIntegerMain {
    public static void main(String[] args) {
        AtomicInteger i = new AtomicInteger(1);
        i.incrementAndGet();
    }
}
