package com.example.springcloudnacosuser.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 动态配置写法1:@RefreshScope+@ConfigurationProperties(
 */
@Data
@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "user", ignoreInvalidFields = true)
public class UserConfig {
    private String enable;
    private List<Integer> blackList;
}
