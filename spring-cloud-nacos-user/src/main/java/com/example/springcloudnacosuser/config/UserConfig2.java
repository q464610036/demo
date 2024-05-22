package com.example.springcloudnacosuser.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 动态配置写法1:@RefreshScope+@value
 */
@Data
@Configuration
@RefreshScope
public class UserConfig2 {
    @Value("{user.enable}")
    private String enable;
    @Value("{user.blackList}")
    private List<Integer> blackList;
}
