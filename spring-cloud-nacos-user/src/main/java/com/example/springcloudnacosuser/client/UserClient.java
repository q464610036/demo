package com.example.springcloudnacosuser.client;

import com.example.springcloudnacosuser.client.fallback.UserFallBackFactory;
import com.example.springcloudnacosuser.config.LoadBalancerConfig;
import com.example.springcloudnacosuser.module.user.entity.User;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "server-user", path = "/user", fallbackFactory = UserFallBackFactory.class)
//指定负载均衡策略为随机，用了这个就不用ribbon
@LoadBalancerClient(value = "spring-cloud-feign-hystrix", configuration = LoadBalancerConfig.class)
public interface UserClient {
    @GetMapping("/user/getOne")
    User getOne(Integer id);

    @GetMapping("/user/getOneBatch")
    List<User> getOneBatch(List<Integer> ids);
}