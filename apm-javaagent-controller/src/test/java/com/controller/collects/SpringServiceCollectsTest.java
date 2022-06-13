package com.controller.collects;

import com.controller.AbstractCollects;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.LoaderClassPath;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author 陈孟飞
 * @date 2021/9/1
 */
class SpringServiceCollectsTest {

    @Test
    void isTarget() throws NotFoundException {
        String className = "com.controller.collects.SpringServiceCollectsTest$SpringServiceCollectsTestMock";
        ClassLoader classLoader = SpringServiceCollectsTest.class.getClassLoader();
        ClassPool pool = new ClassPool(true);
        CtClass ctClass = pool.get(className);
        Boolean flag = SpringServiceCollects.INSTANCE.isTarget(className,classLoader, ctClass);
        Assert.isTrue(flag);
    }

    @Test
    void transform() throws Exception {
        //插桩
        System.setProperty("$bit_server","http://123.56.21.219:8860/receive");
        System.setProperty("$bit_key","c4f3508aee6058f3");
        System.setProperty("$bit_secret","966eedc1903454b8");
        //不能这么写，不然class会提前加载，导致类加载器无法重新加载class，即：ctClass.toClass()报错
//        String className = SpringServiceCollectsTestMock.class.getName();
        String className = "com.controller.collects.SpringServiceCollectsTest$SpringServiceCollectsTestMock";
        ClassLoader classLoader = SpringServiceCollectsTest.class.getClassLoader();
        ClassPool pool = new ClassPool(true);
        //不知道有啥用
//        pool.insertClassPath(new LoaderClassPath(classLoader));
        CtClass ctClass = pool.get(className);
        byte[] classfileBuffer = null;
        SpringServiceCollects.INSTANCE.transform(classLoader,className,classfileBuffer ,ctClass);
        //class载入至当前类加载器
        Class<?> cla = ctClass.toClass();
        //测试接口
        SpringServiceCollectsTestMock mock = new SpringServiceCollectsTestMock();
        mock.test();
        Thread.sleep(200000);
    }

    @Service
    public class SpringServiceCollectsTestMock{
        public void test() throws Exception {
            System.out.println("test");
//            throw new Exception();
        }

        private void test2(){
            System.out.println("test2");
        }

    }

    public static void main(String[] args) {
        System.out.println("javassist begin...");com.controller.collects.SpringServiceCollects instance= com.controller.collects.SpringServiceCollects.INSTANCE;
        AbstractCollects.Statistics statistic =instance.begin("%s","%s");
        try {
            System.out.println("test");
        } catch (Throwable e) {
            System.out.println("javassist error...");instance.error(statistic,e);
        }finally{
            System.out.println("javassist end...");instance.end(statistic);
        }
    }
}