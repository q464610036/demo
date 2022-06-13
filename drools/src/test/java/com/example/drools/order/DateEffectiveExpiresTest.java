package com.example.drools.order;

import com.example.drools.order.entity.DateEntity;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * @author 陈孟飞
 * @date 2021/8/17
 */
public class DateEffectiveExpiresTest {
    //测试规则属性date-effective属性
    @Test
    public void test() throws InterruptedException {
        //设置日期格式
//        System.setProperty("drools.dateformat","yyyy-MM-dd HH:mm");
        KieServices kieServices = KieServices.Factory.get();
        //获得Kie容器对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        //从Kie容器对象中获取会话对象
        KieSession session = kieContainer.newKieSession();
        DateEntity dateEntity = new DateEntity();
        dateEntity.setOpen(1);
        session.insert(dateEntity);
        //激活规则，由Drools框架自动进行规则匹配，如果规则匹配成功，则执行当前规则
//        session.fireAllRules(new RuleNameEqualsAgendaFilter("rule_dateexpires_1"));
        session.fireAllRules();
        //关闭会话
        session.dispose();
    }

}
