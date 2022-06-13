package com.IntroSpector.jdbc;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Properties;

/**
 * @author 陈孟飞
 * @date 2021/8/26
 */
public class DBFactory {
    private static Properties properties = new Properties();
    //使用静态代码块读取配置文件
    static {
        //读取配置文件
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("dbConfig.properties");
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获取驱动类
    public static Object getBean() throws ClassNotFoundException, IllegalAccessException, InstantiationException, IntrospectionException, InvocationTargetException {
        String driverUrl = properties.getProperty("driver");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        //反射获取class
        Class<?> driverClass = Class.forName(driverUrl);
        Object bean = driverClass.newInstance();
        //查看bean的属性
        BeanInfo beanInfo = Introspector.getBeanInfo(driverClass);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();// 取得属性描述器
        //属性描述
        for (PropertyDescriptor pd : pds) {
            String propertyName = pd.getName();
            if ("user".equals(propertyName)) {
                //内省为bean赋值
                Method method = pd.getWriteMethod();
                method.invoke(bean, user);
            } else if ("password".equals(propertyName)) {
                //内省为bean赋值
                Method method = pd.getWriteMethod();
                method.invoke(bean, password);
            } else if ("url".equals(propertyName)) {
                //内省为bean赋值
                Method method = pd.getWriteMethod();
                method.invoke(bean, url);
            }
        }

        /*
        //内省为bean赋值
        PropertyDescriptor pd = new PropertyDescriptor("user", driverClass);
        Method method = pd.getWriteMethod();
        method.invoke(bean, user);

        pd = new PropertyDescriptor("password", driverClass);
        method = pd.getWriteMethod();
        method.invoke(bean, password);

        pd = new PropertyDescriptor("url", driverClass);
        method = pd.getWriteMethod();
        method.invoke(bean, url);
         */
        return bean;
    }


}
