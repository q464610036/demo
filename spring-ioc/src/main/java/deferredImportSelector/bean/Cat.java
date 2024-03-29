package deferredImportSelector.bean;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Lazy
@Component
public class Cat {
    public Cat() {
        System.out.println("Cat 【constructor...】");
    }

    @PostConstruct
    public void init() {
        System.out.println("Cat ... 【init...】");
    }

    @PreDestroy
    public void detory() {
        System.out.println("Cat ... 【detory...】");
    }
}
