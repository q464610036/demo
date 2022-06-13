package com.timedemo;

import javassist.*;

/**
 * @author 陈孟飞
 * @date 2021/8/30
 */
public class JavassistMain {
    /**
     * 对StirngUtil中的两个方法做监听，打印执行时间
     * @param args
     * @throws NotFoundException
     * @throws CannotCompileException
     */
    public static void main(String[] args) throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException {
        StringUtil.appendString(1000);
        //ClassPool对象是一个CtClass对象的容器。一个CtClass对象被构建后，它被记录在ClassPool中。这是因为当编译的原文件关联到CtClass表示的类, 编译器要访问CtClass对象。
        ClassPool pool = new ClassPool(true);
        //获得StringUtil的CtClass
        CtClass targetClass = pool.get("com.timedemo.StringUtil");
        //获得方法
        CtMethod method = targetClass.getDeclaredMethod("appendString");
        /**
         * 为什么不再代码块的前后插入代码呢？
         * before插入：Long t=System.namiaoTime();，after插入：当前时间 - before时间，从而计算出时长。
         * 因为after代码块是无法访问before中的变量的，即无法访问t变量
         */
//        ctMethod.insertBefore(src);
//        ctMethod.insertAfter(src);

        //拷贝appendString方法并重命名为appendString$Agent
        CtMethod agentMethod = CtNewMethod.copy(method, method.getName()+"$Agent", targetClass, null);
        //为class添加方法appendString$Agent
        targetClass.addMethod(agentMethod);
        String src = "{" +
                "long begin = System.nanoTime();"+ //这里不能写成Long，如果要写成Long需要转换$w转换为包装类型
//                "Long begin = ($w)System.nanoTime();"+
                "Object result = "+method.getName()+"$Agent($$);"+
                "long end = System.nanoTime();"+
                "System.out.println(\"耗时：\"+(end-begin));"+
                "return ($r)result;"+
                "}";
        //修改原来的menthod
        method.setBody(src);
        //class载入至当前类加载器
        Class<?> cla = targetClass.toClass();
        StringUtil.appendString(1000);
//        StringUtil stringUtil = (StringUtil)cla.newInstance();
    }
}
