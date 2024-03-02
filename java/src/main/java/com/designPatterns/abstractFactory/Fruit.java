package com.designPatterns.abstractFactory;

public abstract class Fruit extends  AbstractBaseProduct implements IProduct{
    public void printMessage() {
        System.out.println("赠送价值"+this.totalPrice()+"元的"+this.kind);
    }
}
