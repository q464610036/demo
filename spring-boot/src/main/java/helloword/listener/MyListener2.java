package helloword.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MyListener2 implements ApplicationListener<MyEvent> {

    public void onApplicationEvent(MyEvent event){
        System.out.println("MyListener2 收到事件：" + event);
    }
}
