package annotationDriver.config;


import annotationDriver.bean.ColorFactoryBean;
import annotationDriver.condition.LinuxCondition;
import annotationDriver.bean.Person;
import annotationDriver.condition.MyImportBeanDefinitionRegistrar;
import annotationDriver.condition.MyImportSelector;
import annotationDriver.condition.WindowsCondition;
import annotationDriver.bean.Color;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

//也可以使用在类上，表示当操作系统为windows时，该类下所有的bean才生效
//@Conditional(WindowsCondition.class)
//表示该类为配置类
@Configuration
//快速导入color组件
@Import({Color.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class MainConfig {

    //默认是单实例的

    /**
     * ConfigurableBeanFactory#SCOPE_PROTOTYPE
     *
     * @return\
     * @Scope:调整作用域 prototype：多实例的：ioc容器启动并不会去调用方法创建对象放在容器中。
     * 每次获取的时候才会调用方法创建对象；
     * singleton：单实例的（默认值）：ioc容器启动会调用方法创建对象放到ioc容器中。
     * 以后每次获取就是直接从容器（map.get()）中拿，
     * request：同一次请求创建一个实例
     * session：同一个session创建一个实例
     * <p>
     * 懒加载：
     * 单实例bean：默认在容器启动的时候创建对象；
     * 懒加载：容器启动不创建对象。第一次使用(获取)Bean创建对象，并初始化；
     * @see ConfigurableBeanFactory#SCOPE_SINGLETON
     * @see org.springframework.web.context.WebApplicationContext#SCOPE_REQUEST  request
     * @see org.springframework.web.context.WebApplicationContext#SCOPE_SESSION     sesssion
     */

    //多实例
    @Scope("prototype")
    @Bean("person")
    public Person person() {
        System.out.println("给容器中添加Person....");
        return new Person("张三", 25);
    }

    //懒加载
    @Lazy
    //条件过滤 windows系统
    @Conditional(WindowsCondition.class)
    @Bean("bill")
    public Person person01() {
        return new Person("Bill Gates", 62);
    }

    //条件过滤 linux系统
    @Conditional(LinuxCondition.class)
    @Bean("linus")
    public Person person02() {
        return new Person("linus", 48);
    }

    @Bean
    public ColorFactoryBean colorFactoryBean() {
        return new ColorFactoryBean();
    }
}
