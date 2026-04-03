package com.attendance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 考勤Excel处理项目启动类
 * 无数据库，纯Excel解析/处理/导出
 */
@SpringBootApplication
public class AttendanceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AttendanceApplication.class, args);
        System.out.println("=====================================");
        System.out.println("✅ 考勤Excel处理项目启动成功！端口：8080");
        System.out.println("✅ 接口地址：POST http://localhost:8080/att/process");
        System.out.println("=====================================");
    }
}