package annotationDriver.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class Cat implements InitializingBean, DisposableBean {

    @Override
    public void destroy() throws Exception {
        // TODO Auto-generated method stub
        System.out.println("cat...destroy...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub
        System.out.println("cat...afterPropertiesSet...");
    }

}
