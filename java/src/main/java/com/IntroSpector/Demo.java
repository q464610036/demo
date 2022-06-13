package com.IntroSpector;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import org.junit.Test;

/**
 * 使用内省API操作bean的属性
 *
 */
public class Demo {
    // 1.得到bean的所有属性
    @Test
    public void test1() throws Exception {
        BeanInfo info = Introspector.getBeanInfo(Person.class, Object.class);// 不内省从父类继承的属性
        PropertyDescriptor[] pds = info.getPropertyDescriptors();// 取得属性描述器
        //属性描述
        for (PropertyDescriptor pd : pds) {
            System.out.println(pd.getName());
        }
    }

    // 2.操纵bean的指定属性：age
    @Test
    public void test2() throws Exception {
        Person p = new Person();
        //属性描述器
        PropertyDescriptor pd = new PropertyDescriptor("age", Person.class);
        // 得到属性的写方法，为属性赋值
        Method method = pd.getWriteMethod(); // setAge()效果
        method.invoke(p, 24);
        // 获取属性的值
        method = pd.getReadMethod(); // getAge()效果
        System.out.println(method.invoke(p, null));

        //方法描述器
//        MethodDescriptor md = new MethodDescriptor();
    }

    // 3.获取当前操作的属性的类型
    @Test
    public void test3() throws Exception {
        Person p = new Person();
        PropertyDescriptor pd = new PropertyDescriptor("age", Person.class);
        System.out.println(pd.getPropertyType());//结果为int
    }
}

