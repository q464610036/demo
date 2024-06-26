package com.example.springcloudsecurityoauth2uaa.uaa.config;

import com.example.springcloudsecurityoauth2uaa.uaa.handler.*;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

import javax.sql.DataSource;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

/**
 * Security配置类
 * 负责：
 *  1、拦截request
 *  2、账号密码认证
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Resource
    private DataSource dataSource;
    /**
     * spring Security 的过滤器链，用于 Spring Security 的身份认证
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    @Order(2)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
            throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        //有多个权限都可以查询列表可以查询列表
                        .requestMatchers("/user/account/list").hasAnyAuthority("USER_ADD", "USER_DELETE")
                        //有USER_ADD权限才可以添加
                        .requestMatchers("/user/account/add").hasAuthority("USER_ADD")
                        //有USER_DELETE权限才可以删除
                        .requestMatchers("/user/account/delete").hasAuthority("USER_DELETE")
                        //管理员角色可以访问所有order接口
                        .requestMatchers("/order/*").hasRole("ADMIN")
                        // 其他任何请求都需要认证
                        .anyRequest().authenticated()
                )
                // 设置登录表单页面
                .formLogin(Customizer.withDefaults());

        return http.build();
    }

    /**
     * 管理用户名密码，可以基于内存，也可以基于数据库。
     * 可以采用bean配置，也可以写也给配置类DBUserDetailsManager实现UserDetailsManager, UserDetailsPasswordService接口
     * @return
     */
//    @Bean
//    public UserDetailsService userDetailsService() {
//        //内存模式
////        UserDetails userDetails = User.withDefaultPasswordEncoder()
////                .username("user")
////                .password("password")
////                .roles("USER")
////                .build();
////
////        return new InMemoryUserDetailsManager(userDetails);
//        //数据库模式
//        return new JdbcUserDetailsManager(dataSource);
//    }

}