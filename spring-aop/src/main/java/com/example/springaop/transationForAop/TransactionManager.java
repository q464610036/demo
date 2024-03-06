package com.example.springaop.transationForAop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Component
@Aspect
public class TransactionManager {
    @Resource
    private JdbcTemplate jdbcTemplate;

    //impl种所有类、所有方法都增强
    @Pointcut("execution(* com.example.springaop.transationForAop.service.impl.*.*(..))")
    public void pt(){};

//    @Before("pt()")
//    public void begin(){
//        System.out.println("开启事务");
//    }
//
//    @After("pt()")
//    public void commit(){
//        System.out.println("提交事务");
//    }
//
//    @AfterThrowing("pt()")
//    public void rollback(){
//        System.out.println("回滚事务");
//    }
//
//    @AfterReturning("pt()")
//    public void release(){
//        System.out.println("关闭连接");
//    }

    @Around("pt()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object result = null;
        try {
            System.out.println("开启事务");
            result = joinPoint.proceed();
            System.out.println("提交事务");
        } catch ( Throwable e) {
            e.printStackTrace();
            System.out.println("回滚事务");
        } finally {
            System.out.println("关闭连接");
        }
        return result;
    }
}
