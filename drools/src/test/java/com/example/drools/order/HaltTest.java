package com.example.drools.order;

import com.example.drools.order.entity.Student5;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * @author 陈孟飞
 * @date 2021/8/18
 */
public class HaltTest {
    @Test
    public void test() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieClasspathContainer.newKieSession();
        Student5 student1 = new Student5();
        student1.setName("张三");
        student1.setAge(18);
        //将对象插入Working Memory中
        kieSession.insert(student1);
        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
