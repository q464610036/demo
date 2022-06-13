package com.example.drools.order;

import com.example.drools.order.entity.ComparisonOperatorEntity;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.AgendaFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * 比较操作符
 *
 * @author 陈孟飞
 * @date 2021/8/16
 */
public class ComparisonOperatorTest {

    public KieSession getSession() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        //会话对象，用于和规则引擎交互
        KieSession kieSession = kieClasspathContainer.newKieSession();
        return kieSession;
    }

    public void execute(ComparisonOperatorEntity entity, AgendaFilter agendaFilter) {
        KieSession kieSession = this.getSession();
        //将数据提供给规则引擎，规则引擎会根据提供的数据进行规则匹配
        kieSession.insert(entity);
        //激活规则引擎，如果规则匹配成功则执行规则
        kieSession.fireAllRules(agendaFilter);
        //关闭会话
        kieSession.dispose();
    }

    public void execute(ComparisonOperatorEntity entity) {
        KieSession kieSession = this.getSession();
        //将数据提供给规则引擎，规则引擎会根据提供的数据进行规则匹配
        kieSession.insert(entity);
        //激活规则引擎，如果规则匹配成功则执行规则
        kieSession.fireAllRules();
        //关闭会话
        kieSession.dispose();
    }

    @Test
    public void test1() {
        ComparisonOperatorEntity entity = new ComparisonOperatorEntity();
        List<String> list = new ArrayList<>();
        list.add("1");
        entity.setList(list);
        this.execute(entity);
    }

    @Test
    public void test2() {
        ComparisonOperatorEntity entity = new ComparisonOperatorEntity();
        List<String> list = new ArrayList<>();
        list.add("a");
        entity.setList(list);
        entity.setNames("a");
        this.execute(entity);
    }

    @Test
    public void test3() {
        ComparisonOperatorEntity entity = new ComparisonOperatorEntity();
        List<String> list = new ArrayList<>();
        list.add("a");
        entity.setList(list);
        entity.setNames("b");
        this.execute(entity);
    }

    @Test
    public void test4() {
        ComparisonOperatorEntity entity = new ComparisonOperatorEntity();
        entity.setNames("李四");
        this.execute(entity);
    }

    /**
     * 执行指定规则
     */
    @Test
    public void test5() {
        ComparisonOperatorEntity entity = new ComparisonOperatorEntity();
        entity.setNames("李四");
        //只执行matches规则
        this.execute(entity, new RuleNameEqualsAgendaFilter("matches"));
    }
}
