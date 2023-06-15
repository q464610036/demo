package com.example.message.module.message.service;


import com.example.message.module.message.dto.MessageSendDTO;

import java.math.BigInteger;


public interface IMessageService {


    /**
     * 消息推送
     * @param dto
     */
    void send(MessageSendDTO dto) throws Exception;
}
