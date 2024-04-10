package annotationDriver;

import annotationDriver.aop.MathCalculator;
import annotationDriver.bean.*;
import annotationDriver.config.*;
import annotationDriver.ext.ExtConfig;
import annotationDriver.service.BookService;
import annotationDriver.timer.TimerConfig;
import annotationDriver.tx.TxConfig;
import annotationDriver.tx.UserService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class})
public class MainTest {
    public static ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);

    public static void main(String[] args) throws InterruptedException {
        /**
         * 如果容器中定义了两个或两个以上的Person:
         * 	使用根据class获取bean时会报错：因为返回值只有一个bean，程序并不知道你需要哪个bean。
         */
//		test1();

        /**
         * 如果容器中定义了两个或两个以上的Person:
         * 	根据bean的名称获取bean，则不会报错
         */
//		test2();

        /**
         * 获取指定class的所有bean name，并根据操作系统过滤
         * 如果要模拟linux，在运行中设置环境变量： -Dos.name=linux
         */
//		test3();

        /**
         * 打印配置类中所有组件
         */
//		test4();

        /**
         * FactoryBean测试
         */
//		test5();

        /**
         * 生命周期测试:@bean方式
         * 生命周期测试:InitializingBean、DisposableBean方式
         * 生命周期测试:JSR250
         * 生命周期测试:BeanPostProcessor
         */
//		test6();

        /**
         * 自己测试bean生命周期个方法执行顺序
         */
//		test7();

        /**
         * 自动装配
         * 	bookService中注入bookDao
         */
		test8();

        /**
         * @ProFile环境切换
         */
//		test9();

        /**
         * aop
         */
//		test10();

        /**
         * 事务
         */
//		test11();

        /**
         * 扩展原理
         * BeanFactoryPostProcessor
         * BeanDefinitionRegistryPostProcessor
         * ApplicationListener
         */
//        test12();
        /**
         * 定时器
         */
//        test13();
    }

    public static void test1() {
        //此处会报错，因为后面的demo需要，定义了多个Person的bean，当根据Person.class获取bean的时候spring不知道应该给哪个bean
        System.out.println("容器创建完成");
        Person bean = applicationContext.getBean(Person.class);
        Person bean2 = applicationContext.getBean(Person.class);
        //单例模式为true，非单例为false
        System.out.println(bean == bean2);
    }

    public static void test2() {
        Person bill = (Person) applicationContext.getBean("bill");
        System.out.println(bill);
    }

    public static void test3() {
        String[] names = applicationContext.getBeanNamesForType(Person.class);
        for (String name : names) {
            System.out.println(name);
        }
    }

    public static void test4() {
        String[] names = applicationContext.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
    }

    public static void test5() {
        //获取到的对象是Color
        Color color = (Color) applicationContext.getBean("colorFactoryBean");
        System.out.println(color);
        //如果要获取bean对象本身，则需要加&
        ColorFactoryBean colorFactoryBean = (ColorFactoryBean) applicationContext.getBean("&colorFactoryBean");
        System.out.println(colorFactoryBean);
    }

    public static void test6() {
        //使用AnnotationConfigApplicationContext可以实现基于Java的配置类加载Spring的应用上下文，即注解驱动建议使用AnnotationConfigApplicationContext
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
        Car car = (Car) applicationContext.getBean("car");
        Cat cat = (Cat) applicationContext.getBean("cat");
        Dog dog = (Dog) applicationContext.getBean("dog");
        applicationContext.close();
    }

    public static void test7() {
        //使用AnnotationConfigApplicationContext可以实现基于Java的配置类加载Spring的应用上下文，即注解驱动建议使用AnnotationConfigApplicationContext
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle2.class);
        Dog dog = (Dog) applicationContext.getBean("dog");
        applicationContext.close();
    }

    public static void test8() {
        //使用AnnotationConfigApplicationContext可以实现基于Java的配置类加载Spring的应用上下文，即注解驱动建议使用AnnotationConfigApplicationContext
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConifgOfAutowired.class);
        BookService bookService = (BookService) applicationContext.getBean("bookService");
        bookService.print();
        applicationContext.close();
    }

    public static void test9() {
        //使用AnnotationConfigApplicationContext可以实现基于Java的配置类加载Spring的应用上下文，即注解驱动建议使用AnnotationConfigApplicationContext
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //需要激活的环境
        applicationContext.getEnvironment().setActiveProfiles("test");
        //注册配置类
        applicationContext.register(MainConfigOfProfile.class);
        //刷新容器
        applicationContext.refresh();

        String[] names = applicationContext.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
        applicationContext.close();
    }

    public static void test10() {
        //使用AnnotationConfigApplicationContext可以实现基于Java的配置类加载Spring的应用上下文，即注解驱动建议使用AnnotationConfigApplicationContext
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);
        MathCalculator mathCalculator = applicationContext.getBean(MathCalculator.class);
        mathCalculator.div(4, 2);
        applicationContext.close();
    }

    public static void test11() {
        //使用AnnotationConfigApplicationContext可以实现基于Java的配置类加载Spring的应用上下文，即注解驱动建议使用AnnotationConfigApplicationContext
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TxConfig.class);
        UserService userService = applicationContext.getBean(UserService.class);
        userService.insertUser();
        applicationContext.close();
    }

    public static void test12() throws InterruptedException {
        //使用AnnotationConfigApplicationContext可以实现基于Java的配置类加载Spring的应用上下文，即注解驱动建议使用AnnotationConfigApplicationContext
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ExtConfig.class);
        applicationContext.publishEvent(new ApplicationEvent(new String("我发布的事件")) {
        });
//        annotationDriver.ext.UserService userService = applicationContext.getBean(annotationDriver.ext.UserService.class);
        applicationContext.close();
    }

    public static void test13() throws InterruptedException {
        //使用AnnotationConfigApplicationContext可以实现基于Java的配置类加载Spring的应用上下文，即注解驱动建议使用AnnotationConfigApplicationContext
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TimerConfig.class);
        Thread.sleep(10000);
        applicationContext.close();
    }
}

