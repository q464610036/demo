package com.example.springcloudnacosuser.client.fallback;

import com.example.springcloudnacosuser.client.UserClient;
import com.example.springcloudnacosuser.module.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserFallBack implements UserClient {
    /**
     * 服务降级策略
     * @return
     */
    @Override
    public User getOne(Integer id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    @Override
    public List<User> getOneBatch(List<Integer> ids) {
        List<User> list = new ArrayList<>();
        for (Integer id : ids) {
            User user = new User();
            user.setId(id);
            list.add(user);
        }
        return list;
    }
}
