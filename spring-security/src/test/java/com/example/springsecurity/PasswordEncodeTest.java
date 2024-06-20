package com.example.springsecurity;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncodeTest {
    @Test
    public void bCryptPasswordEncoder() {
        Long l = System.currentTimeMillis();
        //工作因子默认是10，最小是4，最大是31，越大约慢
        PasswordEncoder encoder = new BCryptPasswordEncoder(10);
        String encoderPassword = encoder.encode("123456");
        System.out.println(encoderPassword+" 耗时："+(System.currentTimeMillis() - l));
        //校验密码
        PasswordEncoder encoder2 = new BCryptPasswordEncoder(10);
        boolean flag = encoder2.matches("123456", encoderPassword);
        System.out.println(flag);
    }

}
