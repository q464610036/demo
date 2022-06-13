package com.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author 陈孟飞
 * @date 2021/2/25
 * <p>
 * 1、Consumer<T>:消费型接口
 * void accept(T t);
 * <p>
 * 2、Supplier<T>:供给型接口
 * T get();
 * <p>
 * 3、Function<T, R>:函数型接口
 * R apply(T t);
 * <p>
 * 4、Predicate<T>:断言型接口
 * boolean test(T t);
 */
public class Test4 {
    //1、Consumer<T>:消费型接口
    public static void consumer() {
        happy(1000, (a) -> System.out.println("消费：" + a + "元"));
    }

    public static void happy(Integer money, Consumer<Integer> consumer) {
        consumer.accept(money);
    }

    //2、Supplier<T>:供给型接口
    //产生num个整数
    public static List<Integer> getNumList(int num, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(supplier.get());
        }
        return list;
    }

    public static void supplier() {
        //产生10个随机数
        List<Integer> list = getNumList(10, () -> {
            int max = 100, min = 1;
            int ran2 = (int) (Math.random() * (max - min) + min);
            return ran2;
        });
        System.out.println(list);
    }

    //3、Function<T, R>:函数型接口
    //字符串处理，传入一个字符串返回一个字符串
    public static String strHandler(String str, Function<String, String> function) {
        return function.apply(str);
    }

    public static void function() {
        //英文转大写
        String str = strHandler("hello", (a) -> a.toUpperCase());

        //英文转小写
//        String str = strHandler("hello", (a) -> a.toLowerCase());
        System.out.println(str);
    }

    //4、Predicate<T>:断言型接口
    //集合中满足条件的数据
    public static List<Integer> getList(List<Integer> list, Predicate<Integer> predicate) {
        List<Integer> result = new ArrayList<>();
        list.forEach(a -> {
            if (predicate.test(a)) {
                result.add(a);
            }
        });
        return result;
    }

    public static void predicate() {
        List<Integer> list = Arrays.asList(
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10
        );
        //返回集合中的基数
        List<Integer> result = getList(list, a -> {
            if (a % 2 == 1) {
                return true;
            } else {
                return false;
            }
        });
        System.out.println(result);
    }

    public static void main(String[] args) {
        //1、Consumer<T>:消费型接口
//        consumer();

        //2、Supplier<T>:供给型接口
//        supplier();

        //3、Function<T, R>:函数型接口
//        function();

        //4、Predicate<T>:断言型接口
        predicate();
    }
}
