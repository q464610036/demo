package com.designPatterns.factory;

public class TestMain {
    public static void main(String[] args) {
        // 创建工厂对象
        Factory factoryA = new FactoryA();
        // 通过工厂创建具体对象
        Product a = factoryA.createProduct();
        a.work();

        // 创建工厂对象
        Factory factoryB = new FactoryA();
        // 通过工厂创建具体对象
        Product b = factoryB.createProduct();
        b.work();
    }
}

//抽象产品
abstract class Product {
    public abstract void work();
}
//具体产品A
class ProductA extends Product{
    @Override
    public void work() {
        System.out.println("ProductA is working !");
    }
}
//具体产品B
class ProductB extends Product{
    @Override
    public void work() {
        System.out.println("ProductB is working !");
    }
}
//抽象工厂类(也可以是接口)
abstract class Factory{
    public abstract Product createProduct();
}
//具体工厂类(生产产品A)
class FactoryA extends Factory{
    @Override
    public Product createProduct() {
        return new ProductA();
    }
}
//具体工厂类(生产产品B)
class FactoryB extends Factory{
    @Override
    public Product createProduct() {
        return new ProductB();
    }
}
