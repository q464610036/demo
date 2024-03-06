package com.example.springaop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy<T> {
    private T target;

    public DynamicProxy(T tObj){
        this.target = tObj;
    }

    public T getProxy(){
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
                        long cost = System.currentTimeMillis();
                        //实现类的方法
                        Object result = method.invoke(target, args);
                        cost = System.currentTimeMillis() - cost;
                        System.out.println("method "+ method.getName() + " cost time "+cost);
                        return result;
                    }
                });
        return (T)proxy;
    }
}