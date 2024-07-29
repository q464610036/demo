package org.jeecg.modules;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.jeecg.modules.*.mapper")
public class SpringBootTianmaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTianmaApplication.class, args);
    }

}
