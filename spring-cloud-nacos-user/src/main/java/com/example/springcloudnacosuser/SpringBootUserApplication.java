package com.example.springcloudnacosuser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
//注册中心客户端
@EnableDiscoveryClient
@MapperScan("com.example.springcloudnacosuser.module.user.mapper")
@EnableFeignClients
// 设置全局的负载均衡策略
//@LoadBalancerClients(defaultConfiguration =
//        CustomLoadBalancerConfig.class)
public class SpringBootUserApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootUserApplication.class, args);
        System.out.println(applicationContext.getEnvironment().getProperty("user.enable"));
        String[] list = applicationContext.getBeanDefinitionNames();

    }
}
