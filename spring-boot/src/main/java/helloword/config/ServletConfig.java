package helloword.config;

import helloword.filter.MyFilter;
import helloword.servlet.MyServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.Arrays;

@Configuration
@PropertySource("test.properties")
public class ServletConfig {

    @Autowired
    private Environment environment;

    @Value("${test}")
    private String level;

    @Bean
    public ServletRegistrationBean myServlet(){
        String a = environment.getProperty("test");
        String b = environment.getProperty("test2");
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new MyServlet(),"/myServlet");
        return registrationBean;
    }


    /**
     * 配置filter方式一：普通的过滤器注册，filter无法使用spring容器中的对象
     * @return
     */
    @Bean
    public FilterRegistrationBean myFilterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new MyFilter());
        //拦截哪些请求
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/hello", "/myServlet"));
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }

    /**
     * 配置filter方式二：使用DelegatingFilterProxy代理注册过滤器，这样被代理的Filter就可以享受Spring的依赖注入和生命周期管理功能
     * FilterRegistrationBean方式和只能二选一
     * @return
     */
    @Bean
    public Filter myFilter(){
        return new MyFilter();
    }
    @Bean
    public FilterRegistrationBean<DelegatingFilterProxy> filterRegistrationBean() {
        FilterRegistrationBean<DelegatingFilterProxy> registrationBean = new FilterRegistrationBean<>();
        //这里注入的是myFilter bean，受spring 容器管理
//        registrationBean.setFilter(new DelegatingFilterProxy("myFilter"));
        registrationBean.setFilter(new DelegatingFilterProxy());
        registrationBean.addInitParameter("targetBeanName",  "myFilter");
        registrationBean.addUrlPatterns("/hello", "/myServlet");
        return registrationBean;
    }

    /*
    //原写法，已停用
    @Bean
    public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer(){
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer continer) {
                continer.setPort(8083);
            }
        };
    }
     */

    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryWebServerFactoryCustomizer(){
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            //定制嵌入式的Servlet规则
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                factory.setPort(8083);
            }
        };
    }
}
