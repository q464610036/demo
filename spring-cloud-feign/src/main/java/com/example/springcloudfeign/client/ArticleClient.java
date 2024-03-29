package com.example.springcloudfeign.client;

import com.example.springcloudfeign.config.LoadBalancerConfig;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "article", fallback = LoadBalancerConfig.class)
//指定负载均衡策略为随机，用了这个就不用ribbon
@LoadBalancerClient(value = "article", configuration = LoadBalancerConfig.class)
public interface ArticleClient {
    //@LoadBalanced(可以写，也可以不用写，默认所有方法都自动加 @LoadBalanced)
    @GetMapping("/article/start")
    String userStart();

}