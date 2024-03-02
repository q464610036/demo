package com.designPatterns.abstractFactory;

public abstract class Car extends  AbstractBaseProduct implements IProduct{
    //汽车要根据单价数量等计算出合计多少钱
    public void printMessage() {
        System.out.println(this.kind +",单价:"+this.price+",数量:"+this.num+this.numUnit+",合计:"+this.totalPrice());
    }
}
