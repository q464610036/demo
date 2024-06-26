package com.example.springcloudsecurityoauth2user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//注册中心客户端
@EnableDiscoveryClient
//@MapperScan("com.example.springcloudnacosuser.module.user.mapper")
@EnableFeignClients
public class SpringCloudSecurityOauth2UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudSecurityOauth2UserApplication.class, args);
    }

}
