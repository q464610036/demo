package com.lambda;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author 陈孟飞
 * 按条件过滤集合
 * @date 2021/2/19
 */
public class Test1 {

    public static List<PeopleVO> getList() {
        List<PeopleVO> list = Arrays.asList(
                new PeopleVO("零", 18, 60),
                new PeopleVO("壹", 20, 70),
                new PeopleVO("贰", 55, 76),
                new PeopleVO("叁", 42, 40),
                new PeopleVO("肆", 31, 80)
        );
        return list;
    }

    public static List<StudentVO> getList2() {
        List<StudentVO> list = Arrays.asList(
                new StudentVO("零", 18, "1年1班", 60),
                new StudentVO("壹", 20, "1年1班", 70),
                new StudentVO("贰", 55, "1年1班", 76),
                new StudentVO("叁", 42, "1年2班", 40),
                new StudentVO("肆", 31, "1年2班", 80)
        );
        return list;
    }

    //策略模式
    public static List<PeopleVO> filterPeople(List<PeopleVO> list, MyPredicate<PeopleVO> myPredicate) {
        List<PeopleVO> data = new ArrayList<>();
        for (PeopleVO vo : list) {
            if (myPredicate.test(vo)) {
                data.add(vo);
            }
        }
        return data;
    }

    //优化方式1（策略模式）：
    public static void test1() {
        List<PeopleVO> list = getList();
        //按照年龄过滤
        list = filterPeople(list, new FilterAge());
        //按照体重过滤
        list = filterPeople(list, new FilterWeight());
        System.out.println("年龄超过30，体重超过130的员工");
        list.forEach(a -> {
            System.out.println("姓名：" + a.getName() + " 年龄：" + a.getAge() + " 体重：" + a.getWeight());
        });
    }

    //优化方式2（策略模式 + 匿名内部类）：
    public static void test2() {
        List<PeopleVO> list = getList();
        //按照年龄过滤
        list = filterPeople(list, new MyPredicate<PeopleVO>() {
            @Override
            public boolean test(PeopleVO peopleVO) {
                return peopleVO.getAge() > 20;
            }
        });

        list = filterPeople(list, new MyPredicate<PeopleVO>() {
            @Override
            public boolean test(PeopleVO peopleVO) {
                return peopleVO.getWeight() > 65;
            }
        });

        System.out.println("年龄超过30，体重超过130的员工");
        list.forEach(a -> {
            System.out.println("姓名：" + a.getName() + " 年龄：" + a.getAge() + " 体重：" + a.getWeight());
        });
    }

    //优化方式3（策略模式 + lambda表达式）：
    public static void test3() {
        List<PeopleVO> list = getList();
        //按照年龄过滤
        list = filterPeople(list, (b) -> b.getAge() > 20);
        list = filterPeople(list, (b) -> b.getWeight() > 65);
        System.out.println("年龄超过30，体重超过130的员工");
        list.forEach(a -> {
            System.out.println("姓名：" + a.getName() + " 年龄：" + a.getAge() + " 体重：" + a.getWeight());
        });
    }

    //优化方式4（stream）：
    public static void test4() {
        List<PeopleVO> list = getList();
        list.stream()
                .filter((e) -> e.getAge() > 20)
                .filter((e) -> e.getWeight() > 65)
                .forEach(a -> {
                    System.out.println("姓名：" + a.getName() + " 年龄：" + a.getAge() + " 体重：" + a.getWeight());
                });

    }

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        LocalDate d = new Date(System.currentTimeMillis()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//        System.out.println(d.getMonthValue());
//        test4();

        System.out.println(LocalTime.now().getLong(ChronoField.SECOND_OF_DAY));
    }


}
