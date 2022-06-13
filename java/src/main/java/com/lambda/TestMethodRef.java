package com.lambda;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 方法引用与构造引用
 * <p>
 * 一、方法引用：若lambda体中内容有方法已实现了，我们可以使用“方法引用”
 * （可理解为方法引用是lambda表达式的另外一种表现方式）
 * <p>
 * 1、对象::实例方法名
 * <p>
 * 2、类::静态方法名
 * <p>
 * 3、类::实例方法名
 * <p>
 * 二、构造引用：调用的构造器是无参还是有参取决于函数式接口方法存在几个入参，有几个参数则会调用几个参数的构造器。
 * 例如：Supplier的方法无参，则调用无参构造方法。Function的方法有一个参数，则会调用一个参数的构造方法
 * <p>
 * ClassName::new
 * <p>
 * 三、数字引用:
 * type::new
 *
 * @author 陈孟飞
 * @date 2021/3/2
 */
public class TestMethodRef {

    //方法引用(3种)
    //1、对象::实例方法名
    public static void test1() {
        //如果要println，lambda可以写成如下：
        Consumer<String> consumer = e -> System.out.println();
        //还可以简化为（省略了参数和括号等）：
        PrintStream p = System.out;
        Consumer<String> consumer2 = p::println;
        consumer2.accept("hello word");

        StudentVO stundent = new StudentVO();
        stundent.setName("洛克");
        Supplier<String> supplier = stundent::getName;
        System.out.println(supplier.get());
    }

    //2、类::静态方法名
    public static void test2() {
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
        //可以简化为（省略了参数和括号等）：
        Comparator<Integer> comparator1 = Integer::compare;
        comparator1.compare(1, 2);

    }

    //3、类::实例方法名
    //注意：lambda参数列表中，第一个参数是方法的调用者，第二个参数是方法的参数时，才可以使用。即：x是方法调用者，y是方法参数
    public static void test3() {
        BiPredicate<String, String> bip = (x, y) -> x.equals(y);
        //可以简化为（省略了参数和括号等）：
        BiPredicate<String, String> bip2 = String::equals;
        bip2.test("A", "B");
    }

    //构造器引用
    //1、无参构造引用
    public static void test4() {
        Supplier<StudentVO> supplier = () -> new StudentVO();
        //简化为
        Supplier<StudentVO> supplier2 = StudentVO::new;
        StudentVO studnetVO = supplier2.get();
    }

    //2、一个参数构造引用
    public static void test5() {
        Function<Integer, StudentVO> fun = (x) -> new StudentVO(x);
        //简化为
        Function<Integer, StudentVO> fun2 = StudentVO::new;
        StudentVO studnetVO = fun2.apply(100);
    }

    //数组引用
    public static void test6() {
        Function<Integer, String[]> fun = (x) -> new String[x];
        fun.apply(4);
        //简化为
        Function<Integer, String[]> fun2 = String[]::new;
        fun2.apply(5);
    }
}