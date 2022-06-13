package com.workbench.service;

import com.demo.demo.order.entity.Order;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 陈孟飞
 * @date 2021/8/19
 */
@Service
public class OrderService {
    @Autowired
    private KieBase kieBase;
    public Double rule(){
        Order order = new Order();
        order.setOriginalPrice(100D);
        KieSession kieSession = kieBase.newKieSession();
        kieSession.insert(order);
        kieSession.fireAllRules();
        kieSession.dispose();
        System.out.println("优惠价格："+order.getRealPrice());
        return order.getRealPrice();
    }
}
