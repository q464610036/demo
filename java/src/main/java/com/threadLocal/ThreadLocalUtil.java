package com.threadLocal;

/**
 * @author 陈孟飞
 * @date 2021/7/21
 */
public class ThreadLocalUtil {
    private static ThreadLocal<Student> tstudent = new ThreadLocal<Student>();

    public static ThreadLocal<Student> getStudentThreadLocal() {
        return tstudent;
    }

    public static void setStudentThreadLocal(Student s) {
        tstudent.set(s);
    }


}
