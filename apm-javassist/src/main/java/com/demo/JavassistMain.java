package com.demo;

import javassist.*;

import java.lang.reflect.Method;

/**
 * @author 陈孟飞
 * @date 2021/8/30
 */
public class JavassistMain {

    public static void main(String[] args) throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException {
        //ClassPool对象是一个CtClass对象的容器。一个CtClass对象被构建后，它被记录在ClassPool中。这是因为当编译的原文件关联到CtClass表示的类, 编译器要访问CtClass对象。
        ClassPool pool = new ClassPool(true);
        //不知道什么用，将JavassistMain加入到pool容器(这里加不加都没区别，没有用到)
        pool.insertClassPath(new LoaderClassPath((JavassistMain.class.getClassLoader())));
        //动态构建一个class，这个class就是JavassistMain织入代码后的class
        CtClass targetClass = pool.makeClass("com.demo.Hello");
        //让它实现一个接口
        targetClass.addInterface(pool.get(IHello.class.getName()));
        //返回值类型，这里没有返回值
        CtClass returnType = pool.get(void.class.getName());
        //方法名称
        String methodName = "sayHello";
        //方法入参
        CtClass[] parameter = new CtClass[]{pool.get(String.class.getName())};
        //创建一个方法
        CtMethod method = new CtMethod(returnType, methodName, parameter, targetClass);
        //方法体代码
        String str = "{" +
                "int a = System.currentTimeMillis();"+
                "System.out.println(a);"+
                "long b = System.currentTimeMillis();"+
                "System.out.println(b);"+
                    "System.out.println(\"hello\"+$1);"+
                "}";
        method.setBody(str);
        //为class添加方法
        targetClass.addMethod(method);

        //获取新构建的class
        Class cla = targetClass.toClass();
        //实例化对象并强转为接口
        IHello iHello = (IHello)cla.newInstance();
        iHello.sayHello("小灰灰");
    }
}
