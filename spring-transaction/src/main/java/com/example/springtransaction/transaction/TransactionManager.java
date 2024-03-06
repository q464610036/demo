package com.example.springtransaction.transaction;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
@Aspect
public class TransactionManager {
    @Resource
    private DataSource dataSource;

    //impl种所有类、所有方法都增强
    @Pointcut("execution(* com.example.springtransaction.transaction.service.impl.*.*(..))")
    public void pt(){};

    @Around("pt()")
    public Object around(ProceedingJoinPoint joinPoint) throws SQLException {
        Object result = null;
        //通过spring事务同步管理器对象把Connection和线程绑定
        TransactionSynchronizationManager.initSynchronization();
        //根据dataSource拿到数据库连接
        Connection connection = DataSourceUtils.getConnection(dataSource);
        try {
            System.out.println("开启事务");
            //改变connection事务提交策略为手动提交
            connection.setAutoCommit(false);
            result = joinPoint.proceed();
            System.out.println("提交事务");
            //提交事务
            connection.commit();
        } catch ( Throwable e) {
            e.printStackTrace();
            System.out.println("回滚事务");
            //事务回滚
            connection.rollback();
        } finally {
            System.out.println("关闭连接");
            //关闭连接
            connection.close();
        }
        return result;
    }
}
