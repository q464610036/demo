package annotationDriver.ext;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvcConfig支持跨域
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    /**
     * 添加跨域支持
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/user/*") //服务器哪些接口支持跨域
                .allowedOrigins("http://localhost:8080") //支持哪些来源域名跨域,*代表所有
                .allowedHeaders("GET","POST"); //支持哪些请求类型,*代表所有
    }
}
