package com.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈孟飞
 * @date 2022/4/29
 */
public class Test {
    static int countLegs(List<? extends Animal> animals) {
        int retVal = 0;
        for (Animal animal : animals) {
            retVal += animal.getCountLegs();
        }
        return retVal;
    }

    static int countLegs1(List<Animal> animals) {
        int retVal = 0;
        for (Animal animal : animals) {
            retVal += animal.getCountLegs();
        }
        return retVal;
    }

    public static void main(String[] args) {
        List<Dog> dogs = new ArrayList<>();
        //  不会报错
        countLegs(dogs);
        //  报错
//        countLegs1(dogs);
    }

//    private  <K extends A, E extends B> E test(K arg1, E arg2){
//        E result = arg2;
//        arg2.compareTo(arg1);
//        //.....
//        return result;
//    }

//    private <T> void test(List<? super T> dst, List<T> src) {
//        for (T t : src) {
//            dst.add(t);
//        }
//    }
//
//    public static void main(String[] args) {
//        List<Dog> dogs = new ArrayList<>();
//        List<Animal> animals = new ArrayList<>();
//        new Test().test(animals, dogs);
//    }

    
}
