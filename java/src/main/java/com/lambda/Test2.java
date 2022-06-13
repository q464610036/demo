package com.lambda;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * @author 陈孟飞
 * Lambda基础语法：引用新的操作符 -> 称之为箭头操作符或lambda操作符。
 * 箭头操作符将lambda拆为两部分：
 * 左侧：lambda所需的参数列表（抽象方法入参有几个就写几个，保持一致。Test1中的MyPredicate test方法的入参就是lambda左侧的参数列表）
 * 右侧：lambda实现的功能（抽象方法的实现。Test1中的MyPredicate test接口实现）
 * <p>
 * 语法格式一：无参数无返回值
 * () -> System.out.println("hello lambda");
 * <p>
 * 语法格式二：有一个参数，无返回值
 * (e) -> System.out.println(e);
 * <p>
 * 语法格式三：若只有一个参数，则参数的小括号可省略不写
 * e -> System.out.println(e);
 * <p>
 * 语法格式四：有多个参数，有返回值，lambda体中有多条语句
 * (x,y) -> {
 * System.out.println("比较大小");
 * return Integer.compare(x, y);
 * };
 * <p>
 * 语法格式五：有多个参数，有返回值，lambda体中只有一条语句，大括号与return都可不写
 * Comparator<Integer> com = (x,y) -> Integer.compare(x, y);
 * <p>
 * 语法格式六：参数列表的参数类型可写可不写，因为java编译器可以通过上下文推断类型，这个过程称之为“类型推断”
 * Comparator<Integer> com = (Integer x, Integer y) -> Integer.compare(x, y);
 * <p>
 * Lambda表达式需要函数式接口的支持。
 * 函数式接口：即接口只有一个抽象方法。可用@FunctionalInterface注解修饰，检查是否是函数式接口。
 * <p>
 * 总结：lambda表达式就是对一个函数式接口进行实现
 * @date 2021/2/19
 */
public class Test2 {

    //语法格式一：无参数无返回值
    public static void test1() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello lambda");
            }
        };
        r.run();
        System.out.println("----------------------");
        Runnable r1 = () -> System.out.println("hello lambda");
        r1.run();
    }

    //语法格式二：有一个参数，无返回值
    public static void test2() {
        Consumer<String> con = (e) -> System.out.println(e);
        con.accept("你好");
    }

    //语法格式四：有多个参数，有返回值，lambda体中有多条语句
    public static void test5() {
        //比较大小
        Comparator<Integer> com = (x, y) -> {
            System.out.println("比较大小");
            return Integer.compare(x, y);
        };
    }

    //语法格式五：有多个参数，有返回值，lambda体中只有一条语句，大括号与return都可不写
    public static void test6() {
        //比较大小
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
    }

    //语法格式六：参数类型可写可不写
    public static void test7() {
        //比较大小
        Comparator<Integer> com = (Integer x, Integer y) -> Integer.compare(x, y);
    }

    //语法格式七：
    public static void test8() {

    }

    public static void main(String[] args) {
        test2();
    }
}
