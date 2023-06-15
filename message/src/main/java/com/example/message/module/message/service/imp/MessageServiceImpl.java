package com.example.message.module.message.service.imp;

import com.example.message.module.common.util.QQSMSUtils;
import com.example.message.module.message.dto.MessageSendDTO;
import com.example.message.module.message.service.IMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MessageServiceImpl implements IMessageService {


    @Override
    public void send(MessageSendDTO dto) throws Exception {
        List<String> toEmailList = new ArrayList<>();
        toEmailList.add("464610036@qq.com");
        toEmailList.add("38755065@qq.com");
        //发送邮件
        QQSMSUtils.send(toEmailList, dto.getTitle(), dto.getContent());
        //TODO 推送微信公众号
    }
}
