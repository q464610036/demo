package com.threadLocal;

/**
 * @author 陈孟飞
 * @date 2021/7/21
 */
public class ServiceImpl {
    public void test() {
        Student student = ThreadLocalUtil.getStudentThreadLocal().get();
        System.out.println(Thread.currentThread().getName() + ":" + student.getName());
    }
}
