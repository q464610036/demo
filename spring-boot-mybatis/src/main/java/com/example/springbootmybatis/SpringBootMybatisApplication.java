package com.example.springbootmybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBootMybatisApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootMybatisApplication.class, args);
        String[] list = applicationContext.getBeanDefinitionNames();
        for (String beanName : list) {
            System.out.println(beanName);
        }
    }

}
