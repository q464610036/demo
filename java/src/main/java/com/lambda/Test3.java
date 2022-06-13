package com.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 1、list按年龄排序
 * 2、数组按年龄排序
 *
 * @author 陈孟飞
 * @date 2021/2/25
 */
public class Test3 {

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


    public static PeopleVO[] getArray() {
        PeopleVO[] array = new PeopleVO[5];
        array[0] = new PeopleVO("零", 18, 60);
        array[1] = new PeopleVO("壹", 20, 70);
        array[2] = new PeopleVO("贰", 55, 76);
        array[3] = new PeopleVO("叁", 42, 40);
        array[4] = new PeopleVO("肆", 31, 80);
        return array;
    }

    public static void main(String[] args) {
        //1、list按年龄排序
        List<PeopleVO> list = getList();
        Collections.sort(list, (e1, e2) -> {
            //正排
            return e1.getAge().compareTo(e2.getAge());
            //倒排
//            return -e1.getAge().compareTo(e2.getAge());
        });
        list.forEach(a -> {
            System.out.println("姓名：" + a.getName() + " 年龄：" + a.getAge() + " 体重：" + a.getWeight());
        });
        System.out.println("----------------------------------------------------------------------");

        //2、数组按年龄排序
        PeopleVO[] list2 = getArray();
        Arrays.sort(list2, (e1, e2) ->
                e1.getAge().compareTo(e2.getAge())
        );
        for (PeopleVO a : list2) {
            System.out.println("姓名：" + a.getName() + " 年龄：" + a.getAge() + " 体重：" + a.getWeight());
        }
    }

}
