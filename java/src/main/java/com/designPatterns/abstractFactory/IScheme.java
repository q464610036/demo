package com.designPatterns.abstractFactory;

public interface IScheme {
    Car buyCar(Integer num, String numUnit);
    Fruit offerFruit(Integer num, String numUnit);
}
