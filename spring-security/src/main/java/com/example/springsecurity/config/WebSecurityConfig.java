package com.example.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
//@EnableWebSecurity //开启springSecurity，如果是springBoot项目可以不加这个注解
public class WebSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        //创建基于用户内存的信息管理器
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        //使用manager管理UserDetails对象
        manager.createUser(
                //创建UserDetails对象，用户管理用户名、密码、角色、权限等内容
                User.withDefaultPasswordEncoder().username("admin2").password("1234567").roles("USER").build()
        );
        return manager;
    }
}