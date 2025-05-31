package com.lock;


import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 手撸一个CAS锁
 */
public class MyLock {
    //不能用Integer类型
    private static long stateOffset;
    private static Unsafe unsafe;
    public volatile int state;
    static {
        try {
            /**
             * 反射获得Unsafe，我也不知道为什么一定要反射
             * Unsafe 为单例实现，并且 getUnsafe()​ 静态方法仅在调用的类为引导类加载器 BootstrapClassLoader 加载时才合法，直接反射获取 Unsafe 实例吧！
             */
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            //设置为可以存取
            field.setAccessible(true);
            //获取变量的值
            unsafe = (Unsafe) field.get(null);
            //获取state的偏移量，我也不知道是什么鬼
            stateOffset = unsafe.objectFieldOffset(MyLock.class.getDeclaredField("state"));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void lock(){
        while(!unsafe.compareAndSwapInt(this, stateOffset, 0, 1)){
            System.out.println(Thread.currentThread().getName()+":正在尝试获取锁");
        }
        System.out.println(Thread.currentThread().getName()+":获取到了锁");
    }

    public void unlock(){
        System.out.println(Thread.currentThread().getName()+":解锁");
        state = 0;
    }
}
