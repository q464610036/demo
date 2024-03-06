package annotation;

import java.lang.reflect.Method;

/**
 * @author 陈孟飞
 * @date 2021/6/10
 */
public class MetaAnnotationMain {
    public static void main(String[] args) {
        classAnnotation();
        funAnnotation();
    }

    //获取class注解
    public static void classAnnotation() {
        try {
            //获取Person的Class对象
            Class clazz = MetaAnnotation.class;
            //判断person对象上是否有Info注解
            if (clazz.isAnnotationPresent(MyAnnotation2.class)) {
                System.out.println(clazz.getName() + "类上配置了" + MyAnnotation2.class.getName() + "注解！");
                //获取该对象上Info类型的注解
                MyAnnotation2 annotation = (MyAnnotation2) clazz.getAnnotation(MyAnnotation2.class);
                System.out.println("name :" + annotation.name() + ",age:" + annotation.age());
            } else {
                System.out.println(clazz.getName() + "类上没有配置" + MyAnnotation2.class.getName() + "注解！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取方法注解
     */
    public static void funAnnotation() {
        try {
            //获取Person的Class对象
            Class clazz = MetaAnnotation.class;
            //获取方法
            Method stuMethod = clazz.getMethod("test");
            if (stuMethod.isAnnotationPresent(MyAnnotation1.class)) {
                System.out.println(clazz.getName() + "类" + stuMethod.getName() + "方法上配置了" + MyAnnotation1.class.getName() + "注解！");
                //获取该元素上指定类型的注解
                MyAnnotation1 annotation = stuMethod.getAnnotation(MyAnnotation1.class);
                System.out.println("name: " + annotation.name() + ", age: " + annotation.age());
            } else {
                System.out.println(clazz.getName() + "类" + stuMethod.getName() + "方法没有" + MyAnnotation1.class.getName() + "注解！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
