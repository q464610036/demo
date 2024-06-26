package com.example.springsecurity.config;

import com.example.springsecurity.handler.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//@EnableWebSecurity //开启springSecurity，如果是springBoot项目可以不加这个注解
@EnableMethodSecurity //基于方法的授权，用了这个就不用在filterChain的 http.authorizeRequests配置了
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //开启授权保护
//        http.authorizeHttpRequests(authorize -> authorize
//                .anyRequest() //对所有请求开启授权保护
//                .authenticated() //已认证的请求会被自动授权
//        );
        //开启授权保护，authorizeHttpRequests和authorizeRequests只能配置一个
        http.authorizeRequests(authorize -> authorize
//                //有多个权限都可以查询列表可以查询列表
//                .requestMatchers("/user/account/list").hasAnyAuthority("USER_ADD", "USER_DELETE")
//                //有USER_ADD权限才可以添加
//                .requestMatchers("/user/account/add").hasAuthority("USER_ADD")
//                //有USER_DELETE权限才可以删除
//                .requestMatchers("/user/account/delete").hasAuthority("USER_DELETE")
//                //管理员角色可以访问所有order接口
//                .requestMatchers("/order/*").hasRole("ADMIN")
                //对所有请求开启授权保护或不保护
                .anyRequest()
                //对任何请求都需要认证
                .authenticated()
                //对所有请求允许，和authenticated时互斥的
                // .permitAll()
        );
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

    /*
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //开启授权保护
        http.authorizeHttpRequests(authorize -> authorize
                        .anyRequest() //对所有请求开启授权保护
                        .authenticated() //已认证的请求会被自动授权
                )
                //自定义表单授权方式
                .formLogin(form -> {
                   form.loginPage("/myLogin").permitAll() //自定义登录页面，permitAll()表示无需授权就能访问
                           .usernameParameter("username") //自定义用户名参数，默认是username
                           .passwordParameter("password") //自定义用户密码，默认是password
                           .failureUrl("/myLogin?failure") //校验失败跳转地址
                           .failureHandler(new MyAuthenticationFailureHandler()) //自定义失败信息
                           .successHandler(new MyAuthenticationSuccessHandler())//自定义成功信息，用于前后端分离
                   ;

                });
                //默认表单授权方式
//                .formLogin(Customizer.withDefaults())
                //默认基本授权方式
//                .httpBasic(Customizer.withDefaults())
                ;
        http.csrf(csrf -> csrf.disable()); //关闭csrf攻击防御
        return http.build();
    }
     */

    /**
     * 管理用户名密码，可以基于内存，也可以基于数据库。
     * 可以采用bean配置，也可以写也给配置类DBUserDetailsManager实现UserDetailsManager, UserDetailsPasswordService接口
     * @return
     */
//    @Bean
//    public UserDetailsService userDetailsService() {
//        //创建基于用户内存的信息管理器
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        //使用manager管理UserDetails对象
//        manager.createUser(
//                //创建UserDetails对象，用户管理用户名、密码、角色、权限等内容
//                User.withDefaultPasswordEncoder().username("admin2").password("123456").roles("USER").build()
//        );
//        return manager;
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        //密码不加密
//        return NoOpPasswordEncoder.getInstance();
        //BCrypt密码加密方式
        return new BCryptPasswordEncoder();

    }
}