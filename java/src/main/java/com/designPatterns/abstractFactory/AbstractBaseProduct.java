package com.designPatterns.abstractFactory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//抽象基类
public abstract class AbstractBaseProduct {
    public String kind;
    public Integer num;
    public Float price;
    public String numUnit;
    public float totalPrice(){
        return this.num * this.price;
    }
}
