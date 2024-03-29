package annotationDriver.timer;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class MySchedule {
//    @Scheduled(cron="0/5****")
    //或直接设定时间间隔，5秒
    @Scheduled(fixedRate=5000)
    private void configureTasks(){
        System.err.println("执行静态定时任务时间："+ LocalDateTime.now());
    }
}
