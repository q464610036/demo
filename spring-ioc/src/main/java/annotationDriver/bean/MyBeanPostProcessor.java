package annotationDriver.bean;

import annotation.MetaAnnotation;
import annotation.MyAnnotation1;
import annotation.MyAnnotation2;
import annotationDriver.annotation.Photo;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 后置处理器：初始化前后进行处理工作
 * 将后置处理器加入到容器中
 *
 * @author lfy
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(beanName + "postProcessBeforeInitialization...=>" + bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //查询哪些service有@Photo，有的话对返回类型Student增加头像属性，并查询出头像
        Class clazz = bean.getClass();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Photo.class)) {
                System.out.println("method :" + method.getName() + " 有@Photo");
                Class returnType = method.getReturnType();
                if (returnType == Student.class) {
                    //TODO 对结果增强，增加头像

                }
            }
        }
        return bean;
    }
}
