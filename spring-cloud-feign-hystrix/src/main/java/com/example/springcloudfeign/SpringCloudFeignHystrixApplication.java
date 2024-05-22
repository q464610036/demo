package com.example.springcloudfeign;

import com.example.springcloudfeign.config.CustomLoadBalancerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
// 设置全局的负载均衡策略
@LoadBalancerClients(defaultConfiguration =
        CustomLoadBalancerConfig.class)
@EnableHystrix
public class SpringCloudFeignHystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudFeignHystrixApplication.class, args);
    }

}
