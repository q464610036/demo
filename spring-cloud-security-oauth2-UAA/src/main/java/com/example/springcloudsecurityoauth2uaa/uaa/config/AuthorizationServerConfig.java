package com.example.springcloudsecurityoauth2uaa.uaa.config;

import com.example.springcloudsecurityoauth2uaa.uaa.handler.*;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

/**
 * Authorization Server配置类
 * 负责：
 *  1、登录、注销、异常、跨域、csrf攻击防御de
 *  2、注册客户端应用
 *  3、令牌发放记录
 *  4、授权确认操作
 *  5、生成jwt
 *  6、jwt解码
 */
public class AuthorizationServerConfig {

    ThreadLocal<Integer> age = new ThreadLocal<>();
    ThreadLocal<String> name = new ThreadLocal<>();

    @Bean
    @Order(1)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http)
            throws Exception {
        // 定义授权服务配置器
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        // 自定义授权页面
        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .oidc(Customizer.withDefaults());	// Enable OpenID Connect 1.0
        //接受用户信息和/或客户端注册的访问令牌
        http.oauth2ResourceServer((resourceServer) -> resourceServer
                .jwt(Customizer.withDefaults()));

        //这下面的和spring-security的WebSecurityConfig filterChain的配置是一致的
        //自定义表单授权方式
        http.formLogin(form -> {
            form
                    .loginPage("/myLogin") //自定义登录页面，需要一个实际的接口
                    .loginProcessingUrl("/myLogin1") //登录按钮，不需要实际的接口
                    .permitAll() //permitAll()表示无需授权就能访问
                    .usernameParameter("username") //自定义用户名参数，默认是username
                    .passwordParameter("password") //自定义用户密码，默认是password
                    .failureUrl("/myLogin?failure") //校验失败跳转地址
                    .failureHandler(new MyAuthenticationFailureHandler()) //自定义失败信息
                    .successHandler(new MyAuthenticationSuccessHandler())//自定义成功信息，用于前后端分离
            ;

        });
        //自定义注销
        http.logout(logout ->{
            logout.logoutSuccessHandler(new MyLogoutSuccessHandler());
        });
        //自定义异常
        http.exceptionHandling(exception -> {
            //未认证接口访问异常
            exception.authenticationEntryPoint(new MyAuthenticationEntryPoint());
            //未授权接口访问异常
            exception.accessDeniedHandler(new MyAccessDeniedHandler());
        });
        //跨域
        http.cors(Customizer.withDefaults());
        //session管理
        http.sessionManagement(session -> {
            //不创建session，token认证方式时使用
//            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            //只允许1个账号登录，超过则之前的账号退出
            session.maximumSessions(1).expiredSessionStrategy(new MySessionInformationExpiredStrategy());
        });


        //默认表单授权方式
//                .formLogin(Customizer.withDefaults())
        //默认基本授权方式
//                .httpBasic(Customizer.withDefaults())
        ;
        http.csrf(csrf -> csrf.disable()); //关闭csrf攻击防御，要开启就注释掉，开启可防止跨站请求攻击
        return http.build();
    }

    /**
     * 注册客户端应用, 对应 oauth2_registered_client 表（spring security自带的表）
     */
    @Bean
    public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate) {
        //内存模式
//        RegisteredClient oidcClient = RegisteredClient.withId(UUID.randomUUID().toString())
//                .clientId("oidc-client")
//                .clientSecret("{noop}secret")
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//                .redirectUri("http://127.0.0.1:8080/login/oauth2/code/oidc-client")
//                .postLogoutRedirectUri("http://127.0.0.1:8080/")
//                .scope(OidcScopes.OPENID)
//                .scope(OidcScopes.PROFILE)
//                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
//                .build();
//
//        return new InMemoryRegisteredClientRepository(oidcClient);
        return new JdbcRegisteredClientRepository(jdbcTemplate);
    }

    /**
     * 令牌的发放记录, 对应 oauth2_authorization 表（spring security自带的表）
     */
    @Bean
    public OAuth2AuthorizationService authorizationService(JdbcTemplate jdbcTemplate, RegisteredClientRepository registeredClientRepository) {
        return new JdbcOAuth2AuthorizationService(jdbcTemplate, registeredClientRepository);
    }

    /**
     * 把资源拥有者授权确认操作保存到数据库, 对应 oauth2_authorization_consent 表（spring security自带的表）
     */
    @Bean
    public OAuth2AuthorizationConsentService authorizationConsentService(JdbcTemplate jdbcTemplate, RegisteredClientRepository registeredClientRepository) {
        return new JdbcOAuth2AuthorizationConsentService(jdbcTemplate, registeredClientRepository);
    }

    /**
     * 加载 JWT 资源, 用于生成令牌
     * @return
     */
    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        KeyPair keyPair = generateRsaKey();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return new ImmutableJWKSet<>(jwkSet);
    }

    private static KeyPair generateRsaKey() {
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        }
        catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }

    /**
     * JWT 解码
     */
    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }
    /**
     * AuthorizationServer的相关配置
     */
    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder().build();
    }

}
