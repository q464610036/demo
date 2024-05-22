package com.example.springcloudnacosuser.client.fallback;

import com.example.springcloudnacosuser.module.user.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * FallBack只能托底，但是无法获取具体的错误信息，所以还需要FallBackFactory获取throwable，记录到日志。
 */
@Component
public class UserFallBackFactory implements FallbackFactory<UserFallBack> {
    private Logger log = LoggerFactory.getLogger(UserFallBackFactory.class);

    @Override
    public UserFallBack create(Throwable throwable) {
        return new UserFallBack(){
            @Override
            public User getOne(Integer id) {
                log.error("被降级了，请排查"+throwable);
                return super.getOne(id);
            }
            @Override
            public List<User> getOneBatch(List<Integer> ids) {
                log.error("被降级了，请排查"+throwable);
                return super.getOneBatch(ids);
            }

        };
    }
}
