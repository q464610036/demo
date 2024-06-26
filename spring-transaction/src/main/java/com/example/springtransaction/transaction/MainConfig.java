package com.example.springtransaction.transaction;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;


@ComponentScan({"com.example.springtransaction.transaction"})
@Configuration
@EnableAspectJAutoProxy
public class MainConfig {

    @Bean
    public DataSource dataSource() throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser("root");
        //模拟密码加密，解析后的密码交给beanPostProcessor处理
        dataSource.setPassword("adfdsdfffdssaa");
//        dataSource.setPassword("admin");
        //已经过时了
//        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        return dataSource;
    }

    @Bean(initMethod = "init", destroyMethod = "destroy" )
    public Car car(){
        return new Car();
    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws Exception {
        //Spring对@Configuration类会特殊处理；给容器中加组件的方法，多次调用都只是从容器中找组件
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        return jdbcTemplate;
    }
}
