package spring.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope
public class Car1 {

    public Car1() {
        System.out.println("car 【constructor...】");
    }

    public void init() {
        System.out.println("car ... 【init...】");
    }

    public void detory() {
        System.out.println("car ... 【detory...】");
    }

}
