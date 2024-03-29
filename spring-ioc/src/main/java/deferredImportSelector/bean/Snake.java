package deferredImportSelector.bean;

import org.springframework.stereotype.Component;

public class Snake {
    public Snake() {
        System.out.println("Snake 【constructor...】");
    }

    public void init() {
        System.out.println("Snake ... 【init...】");
    }

    public void detory() {
        System.out.println("Snake ... 【detory...】");
    }
}
