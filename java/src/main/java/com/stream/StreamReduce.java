package com.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

/**
 * 对一个集合减少，得到一个新的集合
 * @author 陈孟飞
 * @date 2021/10/8
 */
public class StreamReduce {

    @Test
    public void test2() {
        ArrayList<Integer> newList = new ArrayList<Integer>();
        newList.add(100);
        ArrayList<Integer> accResult_ = Stream.of(1, 2, 3, 4)
                //第一个参数，初始值为ArrayList
                .reduce(newList,
                        //第二个参数，实现了BiFunction函数式接口中apply方法，并且打印BiFunction
                        new BiFunction<ArrayList<Integer>, Integer, ArrayList<Integer>>() {
                            @Override
                            public ArrayList<Integer> apply(ArrayList<Integer> acc, Integer item) {
                                acc.add(item);
                                System.out.println("item: " + item);
                                System.out.println("acc+ : " + acc);
                                System.out.println("BiFunction");
                                return acc;
                            }
                            //第三个参数---参数的数据类型必须为返回数据类型，改参数主要用于合并多个线程的result值
                            // （Stream是支持并发操作的，为了避免竞争，对于reduce线程都会有独立的result）
                        }, new BinaryOperator<ArrayList<Integer>>() {
                            @Override
                            public ArrayList<Integer> apply(ArrayList<Integer> acc, ArrayList<Integer> item) {
                                System.out.println("BinaryOperator");
                                acc.addAll(item);
                                System.out.println("item: " + item);
                                System.out.println("acc+ : " + acc);
                                System.out.println("--------");
                                return acc;
                            }
                        });
        System.out.println("accResult_: " + accResult_);

        System.out.println("------------------lambda优化代码-----------------");
//
//        ArrayList<Integer> newList = new ArrayList<>();
//
//        ArrayList<Integer> accResult_s = Stream.of(1,2,3,4)
//                //acc代表newList，item代表accResult_s的一个值
//                .reduce(newList, (acc, item) -> {
//                            acc.add(item);
//                            System.out.println("item: " + item);
//                            System.out.println("acc+ : " + acc);
//                            System.out.println("BiFunction");
//                            return acc;
//                        }, (acc, item) -> null);
//        System.out.println("accResult_s: " + accResult_s);

        ArrayList<Integer> accResult_s = new ArrayList();
        accResult_s.add(1);
        accResult_s.add(2);
        accResult_s.add(3);
        accResult_s.add(4);
        ArrayList<Integer> newaccResult_s = accResult_s.stream()
                //acc代表newList，item代表accResult_s的一个值
                .reduce(newList, (acc, item) -> {
                    acc.add(item);
                    System.out.println("item: " + item);
                    System.out.println("acc+ : " + acc);
                    System.out.println("BiFunction");
                    return acc;
                }, (acc, item) -> null);
        System.out.println("newaccResult_s: " + newaccResult_s);
    }
}
