package com.example.drools.order;

import com.example.drools.order.entity.Color3;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * @author 陈孟飞
 * @date 2021/8/17
 */
public class TimerTest {
    //测试规则属性timer属性
    @Test
    public void test() throws Exception {
        KieServices kieServices = KieServices.Factory.get();
        //获得Kie容器对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        //从Kie容器对象中获取会话对象
        final KieSession session = kieContainer.newKieSession();
        Color3 c = new Color3();
        c.setSystem(0);
        session.insert(c);
        new Thread(new Runnable() {
            public void run() {
                //启动规则引擎进行规则匹配，直到调用halt方法才结束规则引擎
                session.fireUntilHalt();
            }
        }).start();
        Thread.sleep(10000);
        //结束规则引擎
        session.halt();
        //关闭会话
        session.dispose();
    }

    @Test
    public void test2() throws Exception {
        KieServices kieServices = KieServices.Factory.get();
        //获得Kie容器对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        //从Kie容器对象中获取会话对象
        final KieSession session = kieContainer.newKieSession();
        Color3 c = new Color3();
        c.setSystem(1);
        session.insert(c);
        new Thread(new Runnable() {
            public void run() {
                //启动规则引擎进行规则匹配，直到调用halt方法才结束规则引擎
                session.fireUntilHalt();
            }
        }).start();
        Thread.sleep(10000);
        //结束规则引擎
        session.halt();
        //关闭会话
        session.dispose();
    }
}
