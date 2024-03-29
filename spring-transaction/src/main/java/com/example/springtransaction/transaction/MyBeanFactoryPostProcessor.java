package com.example.springtransaction.transaction;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanFactoryPostProcessor...postProcessBeanFactory...");
        int count = beanFactory.getBeanDefinitionCount();
        String[] names = beanFactory.getBeanDefinitionNames();
        System.out.println("当前BeanFactory中有" + count + " 个Bean");
        System.out.println(Arrays.asList(names));
        for (String beanName : names) {
            //判断是否是dataSource
            if ("dataSource".equals(beanName)) {
                //场景：我想在实例化之前就对dataSource的数据库密码解密
                //不能这么写，getBean就是让beanFactory去创建一个bean，如果这个时候就创建bean了后面就不会自动创建bean了，因为容器里已经有了这个bean
//                ComboPooledDataSource dataSource = (ComboPooledDataSource) beanFactory.getBean(beanName);
//                dataSource.setPassword("1111111");
            }
        }
    }
}
