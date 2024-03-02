package com.designPatterns.abstractFactory;

public class SchemeA implements IScheme{
    @Override
    public Car buyCar(Integer num, String numUnit) {
        return new BMW(num, numUnit);
    }

    @Override
    public Fruit offerFruit(Integer num, String numUnit) {
        return new Apple(num, numUnit);
    }
}
