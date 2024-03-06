package com.example.springaop.transation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class TransactionManagerProxy<T> {

    private T target;

    public TransactionManagerProxy(T tObj){
        this.target = tObj;
    }

    public T getProxy(){
        //ClassLoader:类加载器，使用和被代理对象同样的类加载器。固定写法。target.getClass().getClassLoader()
        //Class[]:字节码数组。和被代理对象具有相同的行为，实现相同的接口，固定写法。target.getClass().getInterfaces()
        //InvocationHandler:它是一个接口。提供如何代理的代码，也就是增强的代码。该参数一般都写成匿名内部类，并且他是策略模式的体现。
        Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                new InvocationHandler() {
                    /**
                     * 重新invoke（在反射中invoke就是方法，method是方法名称，会对每一个方法都重写）
                     * @param proxy 代理对象的引用
                     * @param method 方法名称
                     * @param args 方法的入参args[0]是第一个入参，args[1]是第二个入参，
                     * @return
                     * @throws Throwable
                     */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object result = null;
                        try {
                            //开启事务
                            TransactionManager.begin();
                            //执行业务层代码
                            //实现类的方法
                            result = method.invoke(target, args);
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
        return (T)proxy;
    }
}