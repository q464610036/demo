package com.example.springbootcacheable.service;

import com.example.springbootcacheable.vo.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {
//    @Cacheable(value = "users", key = "#userId") //本地缓存
//    @Cacheable(value = "users", key = "#userId", cacheManager = "redisCacheManager") //redis缓存
    @Cacheable(value = "users", key = "#userId", cacheManager = "redisCacheManager") //两级缓存
    public User getUserById(Long userId) {
        // 从数据库或其他数据源获取用户信息
        User user = new User();
        user.setUserId(userId);
        user.setName("DDD");
        return user;
    }
}