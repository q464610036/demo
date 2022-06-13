package com.IntroSpector.jdbc;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 陈孟飞
 * @date 2021/8/26
 */
public class Demo {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException, IntrospectionException, InvocationTargetException {
        Object bean = DBFactory.getBean();
        //内省获取bean的内容
        BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();// 取得属性描述器
        //属性描述
        for (PropertyDescriptor pd : pds) {
            String propertyName = pd.getName();
            Method method = pd.getReadMethod();
            if (method == null) {
                continue;
            }
            Object obj = method.invoke(bean,null);
            if (obj == null) {
                continue;
            }
            System.out.println(propertyName+"="+obj);
        }

        /*
        PropertyDescriptor pd = new PropertyDescriptor("user", bean.getClass());
        Method method = pd.getReadMethod();
        pd = new PropertyDescriptor("password", bean.getClass());
        Method method2 = pd.getReadMethod();
        pd = new PropertyDescriptor("url", bean.getClass());
        Method method3 = pd.getReadMethod();
        System.out.println(method.invoke(bean,null));
        System.out.println(method2.invoke(bean,null));
        System.out.println(method3.invoke(bean,null));
         */
    }
}
