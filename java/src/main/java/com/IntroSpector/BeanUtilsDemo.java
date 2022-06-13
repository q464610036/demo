package com.IntroSpector;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 陈孟飞
 * @date 2021/8/26
 */
public class BeanUtilsDemo {
    @Test
    public void test1() throws IllegalAccessException, InvocationTargetException {
        Person p = new Person();
        BeanUtils.setProperty(p, "name", "yaoer");
        System.out.println(p.getName());
    }

    @Test
    public void test2() throws IllegalAccessException, InvocationTargetException {
        Person p = new Person();
        // 模拟用户提交的表单
        String name = "yaoer";
        String password = "123";
        String age = "24";
        String birthday = "1994-10-12";
        // 给beanUtils注册一个日期转换器
        ConvertUtils.register((type,value)->{
            if (value == null) {
                return null;
            }
            if (!(value instanceof String)) {
                throw new ConversionException("只支持String类型的转换哦！");
            }
            String str = (String) value;
            if (str.trim().equals("")) {
                return null;
            }
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            try {
                return df.parse(str);
            } catch (ParseException e) {
                throw new RuntimeException(e); // 异常链不能断
            }
        }, Date.class);
        // 封装到p对象中
        BeanUtils.setProperty(p, "name", name);
        BeanUtils.setProperty(p, "password", password);
        BeanUtils.setProperty(p, "age", age); // 自动将数据转换（基本类型）
        BeanUtils.setProperty(p, "birthday", birthday);
        System.out.println(p.getName());
        System.out.println(p.getPassword());
        System.out.println(p.getAge());
        System.out.println(p.getBirthday());
    }
    @Test
    public void test3() throws  ConversionException, IllegalAccessException, InvocationTargetException{
        Person p = new Person();
        // 模拟用户提交的表单
        String name = "yaoer";
        String password = "123";
        String age = "24";
        String birthday = "1994-10-12";
        ConvertUtils.register(new DateLocaleConverter(), Date.class);
        BeanUtils.setProperty(p, "name", name);
        BeanUtils.setProperty(p, "password", password);
        BeanUtils.setProperty(p, "age", age); // 自动将数据转换（基本类型）
        BeanUtils.setProperty(p, "birthday", birthday);
        System.out.println(p.getName());
        System.out.println(p.getPassword());
        System.out.println(p.getAge());
        Date date = p.getBirthday();
        System.out.println(date.toString());
    }

    @Test
    public void test4() throws IllegalAccessException, InvocationTargetException {
        Map map = new HashMap();
        map.put("name","zhuzhu");
        map.put("password","123");
        map.put("age","24");
        map.put("birthday","1994-10-12");
        ConvertUtils.register(new DateLocaleConverter(), Date.class);
        Person bean = new Person();
        BeanUtils.populate(bean, map);//用map集合中的值填充bean的属性
        System.out.println(bean.getName());
        System.out.println(bean.getPassword());
        System.out.println(bean.getAge());
        System.out.println(bean.getBirthday());
    }
}
