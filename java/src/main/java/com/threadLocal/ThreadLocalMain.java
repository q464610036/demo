package com.threadLocal;

/**
 * @author 陈孟飞
 * @date 2021/7/20
 */
public class ThreadLocalMain {


    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            Student student = new Student();
            student.setName("A");
            ThreadLocalUtil.setStudentThreadLocal(student);
            new ServiceImpl().test();
        }, "线程1").start();

        new Thread(() -> {
            Student student = new Student();
            student.setName("B");
            ThreadLocalUtil.setStudentThreadLocal(student);
            new ServiceImpl().test();
        }, "线程2").start();
    }


}
