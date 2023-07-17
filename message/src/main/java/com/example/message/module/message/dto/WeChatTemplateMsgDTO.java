package com.example.message.module.message.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WeChatTemplateMsgDTO {
    /**
     * 消息
     */
    private String value;
    /**
     * 消息颜色
     */
    private String color;


    public WeChatTemplateMsgDTO(String value) {
        this.value = value;
        this.color = "#173177";
    }

    public WeChatTemplateMsgDTO(String value, String color) {
        this.value = value;
        this.color = color;
    }
}