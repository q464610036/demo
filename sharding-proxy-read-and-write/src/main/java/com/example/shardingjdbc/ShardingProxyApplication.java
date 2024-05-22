package com.example.shardingjdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.shardingjdbc.module.*.mapper") //添加mapper扫描
public class ShardingProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingProxyApplication.class, args);
    }

}
