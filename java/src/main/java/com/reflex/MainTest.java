package com.reflex;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author 陈孟飞
 * @date 2021/8/25
 */
public class MainTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> classUser = Class.forName("com.java.reflex.User");
//        User user1 = (User)classUser.newInstance();

        //获得所有构造方法
        Constructor<?>[] constructors = classUser.getDeclaredConstructors();
        //获得无参构造方法
        Constructor<?> constructor1 = classUser.getConstructor();
        //获得有一个参数的构造方法
        Constructor<?> constructor2 = classUser.getDeclaredConstructor(String.class);
        constructor2.setAccessible(true);
        User user2 = (User)constructor2.newInstance("你好");
    }
}
