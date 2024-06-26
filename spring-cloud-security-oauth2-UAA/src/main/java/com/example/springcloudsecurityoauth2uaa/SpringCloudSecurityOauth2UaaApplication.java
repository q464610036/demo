package com.example.springcloudsecurityoauth2uaa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//注册中心客户端
@EnableDiscoveryClient
@MapperScan("com.example.springcloudsecurityoauth2uaa.user.mapper")
@EnableFeignClients
public class SpringCloudSecurityOauth2UaaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudSecurityOauth2UaaApplication.class, args);
    }

}
