package com.designPatterns.abstractFactory;

public class CustomerService {
    private IScheme scheme;
    public CustomerService(IScheme scheme){
        this.scheme = scheme;
    }
    public float buyCar(int num,String numUnit){
        Car car = scheme.buyCar(num,numUnit);
        car.printMessage();
        return car.totalPrice();
    }
    public void offerFruit(int num,String numUnit){
        Fruit fruit = scheme.offerFruit(num, numUnit);
        fruit.printMessage();
    }
}
