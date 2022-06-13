package com.jmm;

/**
 * final的内存语义
 *
 * @author 陈孟飞
 * @date 2021/7/20
 */
public class FinalReferenceEscapeExample {
    final int i;
    static FinalReferenceEscapeExample obj;

    public FinalReferenceEscapeExample() {
        i = 1;      // 1
        obj = this; // 2 this引用逸出
    }

    public static void writer() { // 线程1
        new FinalReferenceEscapeExample();
    }

    public static void reader() { // 线程2
        if (obj != null) {
            System.out.println(obj.i);
        }
    }
}
