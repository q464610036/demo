package com.example.springaop.transation;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibMain {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        //被代理对象的字节码
        enhancer.setSuperclass(LoginServiceImpl.class);
        //如何代理
        enhancer.setCallback(new MethodInterceptor() {
            /**
             *
             * @param proxy 代理对象的引用
             * @param method 方法名称
             * @param args 方法的入参args[0]是第一个入参，args[1]是第二个入参，
             * @param methodProxy 当前执行方法的代理对象，一般用不到
             * @return
             * @throws Throwable
             */
            @Override
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy)
                    throws Throwable {
                Object result = null;
                try {
                    //开启事务
                    TransactionManager.begin();
                    //执行业务层代码
                    //实现类的方法
                    result = methodProxy.invokeSuper(proxy, args);
                    System.out.println("代理方法："+method.getName());
                    //提交事务
                    TransactionManager.commit();
                } catch (Exception e) {
                    //事务回滚
                    TransactionManager.rollback();
                } finally {
                    //释放链接
                    TransactionManager.release();
                }
                return result;
            }
        });
        LoginService loginService = (LoginService)enhancer.create();
        loginService.login();
    }

}
