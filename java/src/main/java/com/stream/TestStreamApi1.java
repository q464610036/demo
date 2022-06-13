package com.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author 陈孟飞
 * @date 2021/5/13
 * <p>
 * 一、Stream的三个操作步骤
 * 1、创建strea
 * 通过Collection提供的stream方法(串行流)或parallelStream方法(并行流)
 * 通过Arrays中的静态方法stream获取数组流
 * 通过Stream的静态方法of创建流
 * 2、中间操作
 * 筛选与切片
 * filter：接收lambda，从流中排除某些元素。
 * limit：截断流，使其元素不超过指定数量
 * skip(n)：跳过元素，返回一个扔掉前n个元素的流。若流中元素不足n个，则返回空流。与limit互补。
 * distinct：筛选，通过流生成的hashCode()和equals()去除重复元素
 * 3、终断操作
 * forEach
 */
public class TestStreamApi1 {

    //1、创建strea
    public static void test1() {
        //通过Collection提供的stream方法(串行流)或parallelStream方法(并行流)
        List<Integer> list = new ArrayList<>();
        Stream<Integer> stream1 = list.stream();

        //通过Arrays中的静态方法stream获取数组流
        Integer[] ints = {1, 2, 3};
        Stream<Integer> stream2 = Arrays.stream(ints);

        //通过Stream的静态方法of创建流
        Stream<Integer> stream3 = Stream.of(ints);
    }

    //2、中间操作
    public static void test2() {

    }
}
