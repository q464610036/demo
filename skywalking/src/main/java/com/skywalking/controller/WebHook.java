package com.skywalking.controller;

import com.skywalking.vo.AlarmMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
@RestController
public class WebHook {
    private List<AlarmMessage> lastList = new ArrayList<>();
    //产生告警时调用的接口
    @PostMapping("/webhook")
    public void webhook(@RequestBody List<AlarmMessage> alarmMessageList){
        System.out.println("alarmMessageLis:"+alarmMessageList);
        lastList = alarmMessageList;
    }
    //展示告警的信息
    @GetMapping("/show")
    public List<AlarmMessage> show(){
        return lastList;
    }
}