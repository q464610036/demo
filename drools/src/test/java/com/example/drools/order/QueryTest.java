package com.example.drools.order;

import com.example.drools.order.entity.Student;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;

/**
 * @author 陈孟飞
 * @date 2021/8/18
 */
public class QueryTest {
    @Test
    public void test() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieClasspathContainer.newKieSession();
        Student student1 = new Student();
        student1.setName("张三");
        student1.setAge(12);
        Student student2 = new Student();
        student2.setName("李四");
        student2.setAge(8);
        Student student3 = new Student();
        student3.setName("王五");
        student3.setAge(22);
        //将对象插入Working Memory中
        kieSession.insert(student1);
        kieSession.insert(student2);
        kieSession.insert(student3);
        //调用规则文件中的查询
        QueryResults results1 = kieSession.getQueryResults("query_1");
        int size = results1.size();
        System.out.println("size=" + size);
        for (QueryResultsRow row : results1) {
            Student student = (Student) row.get("$student");
            System.out.println(student);
        }
        //调用规则文件中的查询
        QueryResults results2 = kieSession.getQueryResults("query_2", "王五");
        size = results2.size();
        System.out.println("size=" + size);
        for (QueryResultsRow row : results2) {
            Student student = (Student) row.get("$student");
            System.out.println(student);
        }
        //kieSession.fireAllRules();
        kieSession.dispose();
    }
}
