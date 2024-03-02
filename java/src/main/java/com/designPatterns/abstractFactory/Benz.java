package com.designPatterns.abstractFactory;

public class Benz extends Car{
    public Benz(int num,String numUnit){
        this.kind="奔驰汽车";
        this.price=600000.0f;
        this.num=num;
        this.numUnit=numUnit;
    }
}
