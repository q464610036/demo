package com.example.drools.order;

import com.example.drools.order.entity.Color;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.AgendaFilter;

/**
 * @author 陈孟飞
 * @date 2021/8/17
 */
public class ColorTest {
    public KieSession getSession() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        //会话对象，用于和规则引擎交互
        KieSession kieSession = kieClasspathContainer.newKieSession();
        return kieSession;
    }

    public void execute(Object entity, AgendaFilter agendaFilter) {
        KieSession kieSession = this.getSession();
        //将数据提供给规则引擎，规则引擎会根据提供的数据进行规则匹配
        kieSession.insert(entity);
        //激活规则引擎，如果规则匹配成功则执行规则
        kieSession.fireAllRules(agendaFilter);
        //关闭会话
        kieSession.dispose();
    }

    public void execute(Object entity) {
        KieSession kieSession = this.getSession();
        //将数据提供给规则引擎，规则引擎会根据提供的数据进行规则匹配
        kieSession.insert(entity);
        //激活规则引擎，如果规则匹配成功则执行规则
        kieSession.fireAllRules();
        //关闭会话
        kieSession.dispose();
    }

    @Test
    public void test() {
        Color c = new Color();
        c.setSystem(0);
        this.execute(c);
    }
}
