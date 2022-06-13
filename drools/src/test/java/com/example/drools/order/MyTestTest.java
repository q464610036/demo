package com.example.drools.order;

import com.example.drools.order.entity.MyTestEntity;
import com.example.drools.order.entity.Student3;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * @author 陈孟飞
 * @date 2021/8/19
 */
public class MyTestTest {

    @Test
    public void test(){
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieClasspathContainer.newKieSession();
        MyTestEntity t = new MyTestEntity();
        t.setId(1);
        //将对象插入Working Memory中
        kieSession.insert(t);
        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
