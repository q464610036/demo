package deferredImportSelector.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Snake1 {
    public Snake1() {
        System.out.println("Snake1 【constructor...】");
    }

    @PostConstruct
    public void init() {
        System.out.println("Snake1 ... 【init...】");
    }

    @PreDestroy
    public void detory() {
        System.out.println("Snake1 ... 【detory...】");
    }
}
