package com.designPatterns.abstractFactory;

public class SchemeB implements IScheme{
    @Override
    public Car buyCar(Integer num, String numUnit) {
        return new Benz(num, numUnit);
    }

    @Override
    public Fruit offerFruit(Integer num, String numUnit) {
        return new Pear(num, numUnit);
    }
}
