package helloword.listener;

import org.slf4j.Logger;
import org.springframework.context.ApplicationListener;

public class MyListener implements ApplicationListener<MyEvent> {

    public void onApplicationEvent(MyEvent event){
        System.out.println("MyListener 收到事件：" + event);
    }
}
