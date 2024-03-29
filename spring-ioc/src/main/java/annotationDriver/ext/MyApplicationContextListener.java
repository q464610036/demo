package annotationDriver.ext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationContextListener implements ApplicationListener<ApplicationContextEvent> {

    //当容器中发布此事件以后，方法触发
    @Override
    public void onApplicationEvent(ApplicationContextEvent event) {
        // TODO Auto-generated method stub
        System.out.println("收到ioc容器事件：" + event);
    }
}

