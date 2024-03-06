
package com.circularDependency;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestMain {
    public final static Map<String, Object> objectMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
//        getBean(Man.class);
        getBeanV2(Man.class).getWoman();
        getBeanV2(Woman.class).getMan();
    }


    //解决循环依赖
    public static <T> T getBeanV2(Class<T> beanClass) throws InstantiationException, IllegalAccessException {
        //获取对象的名称
        String beanName = beanClass.getSimpleName().toLowerCase();
        //如果对象存在，直接返回
        if (objectMap.containsKey(beanName)) {
            return (T) objectMap.get(beanName);
        }
        //获取实例对象，等同与new
        Object obj = beanClass.newInstance();
        //刚实例化完就放入map，提前暴露
        objectMap.put(beanName, obj);
        //完成属性填充
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            //针对private
            field.setAccessible(true);
            //获取成员变量对应的对象
            Class<?> fieldClass = field.getType();
            //获取字段名
            String fieldBeanName = fieldClass.getSimpleName().toLowerCase();
            //给成员变量赋值
            field.set(obj, objectMap.containsKey(fieldBeanName) ?
                    objectMap.get(fieldBeanName) : getBeanV2(fieldClass));
        }
        return (T) obj;
    }

    //普通的bean创建，无法解决循环依赖问题
    public static <T> T getBean(Class<T> beanClass) throws InstantiationException, IllegalAccessException {
        //获取实例对象，等同与new
        Object obj = beanClass.newInstance();
        //完成属性填充
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            //针对private
            field.setAccessible(true);
            //获取成员变量对应的对象
            Class<?> fieldClass = field.getType();
            //获取字段名
            String fieldBeanName = fieldClass.getSimpleName().toLowerCase();
            //给成员变量赋值
            field.set(obj, getBean(fieldClass));
        }
        return (T) obj;
    }
}
