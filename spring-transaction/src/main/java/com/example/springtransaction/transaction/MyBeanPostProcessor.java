package com.example.springtransaction.transaction;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        //判断是否是dataSource
        if ("dataSource".equals(beanName)) {
            ComboPooledDataSource dataSource = (ComboPooledDataSource) bean;
            System.out.println("初始化之前的password："+dataSource.getPassword());
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //判断是否是dataSource
        if ("dataSource".equals(beanName)) {
            ComboPooledDataSource dataSource = (ComboPooledDataSource) bean;
            System.out.println("初始化之后的password："+dataSource.getPassword());
            //解析密码
            if ("adfdsdfffdssaa".equals(dataSource.getPassword())) {
                dataSource.setPassword("admin");
            }
            System.out.println("解析之后的password："+dataSource.getPassword());
        }
        return bean;
    }
}
