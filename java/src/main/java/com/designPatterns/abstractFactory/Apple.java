package com.designPatterns.abstractFactory;

public class Apple extends Fruit{
    public Apple(int num,String numUnit){
        this.kind="苹果";
        this.price=8.0f;
        this.num=num;
        this.numUnit=numUnit;
    }
}
