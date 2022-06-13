package com.spring.annotationDriver.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

@Component
public class Dog implements ApplicationContextAware, BeanNameAware, EmbeddedValueResolverAware {
    //@Autowired
    private ApplicationContext applicationContext;

    public Dog() {
        System.out.println("Dog constructor...");
    }

    //对象创建并赋值之后调用
    @PostConstruct
    public void init() {
        System.out.println("Dog init....@PostConstruct...");
    }

    //容器移除对象之前
    @PreDestroy
    public void detory() {
        System.out.println("Dog destory....@PreDestroy...");
    }

    //Aware在构造方法之后、初始化之前执行
    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        String resolveStringValue = resolver.resolveStringValue("你好 ${os.name} 我是 #{20*18}");
        System.out.println("解析的字符串：" + resolveStringValue);
    }

    //Aware在构造方法之后、初始化之前执行
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("Dog applicationContextAware");
        this.applicationContext = applicationContext;
    }

    //Aware在构造方法之后、初始化之前执行
    @Override
    public void setBeanName(String name) {
        System.out.println("Dog beanName:" + name);
    }
}
