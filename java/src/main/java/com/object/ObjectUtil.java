package com.object;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ObjectUtil {
    public static void replaceNullsWithEmptyByList(List list) throws IllegalAccessException {
        for (Object object : list) {
            replaceNullsWithEmpty(object);
        }
    }
    public static void replaceNullsWithEmpty(Object obj) throws IllegalAccessException {
        if (obj == null) {
            return;
        }
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.getType() == String.class) {
                field.setAccessible(true);
                Object value = field.get(obj);
                if (value == null) {
                    field.set(obj, "");
                }
            }
        }
    }

    // 测试用例
    public static void main(String[] args) throws IllegalAccessException {
        Test test = new Test();
        test.a = null;
        test.b = "NotNull";
        test.c = null;
        List list = new ArrayList<>();
        list.add(test);
        replaceNullsWithEmptyByList(list);
        test = (Test)list.get(0);
        System.out.println(test.a); // 输出 ""
        System.out.println(test.b); // 输出 "NotNull"
        System.out.println(test.c); // 输出 ""
    }

    static class Test {
        String a;
        String b;
        String c;
        Integer i = null;
    }
}
