package com.example.message.module.common.util;

import com.alibaba.fastjson.JSONObject;
import com.example.message.module.message.dto.WeChatTemplateMsgDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class WeChatUtils {
    //wxf60e5486be327e4c
    //5017e7c756f82aa845279b214b33e164


    public static String bizSend() {
        // 模板参数
        Map<String, WeChatTemplateMsgDTO> sendMag = new HashMap<>();

        // openId代表一个唯一微信用户，即微信消息的接收人
        String openId = "oNB9p1BpVJEquxxxxxxxxx";
        // 公众号的模板id(也有相应的接口可以查询到)
        String templateId = "B0YStqTYdjHhY9Da9Sy2NM7xxxxxxxxxxx";
        // 微信的基础accessToken
        String accessToken = "57_LubK-8NKQc6C7jsLMxvdHaI0ju4x3-HPWEFhh7GKkw9fKbWhuxxoZyX4GaVIn6y4yO7RKfSlCyHdedKJlHUMZkd8457nKm0TOoaVkbzK1HCZ4g4gZdrmAGBylGBOZu9yxxxxxxxxxxxxxxxx";
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + accessToken;

        /**
         *  其他模板可以从模板库中自己添加
         * 模板ID
         * B0YStqTYdjHhY9Da9Sy2NM7HXxxxxxxxxxxxxxx
         * 开发者调用模板消息接口时需提供模板ID
         * 标题
         * 产品兑付成功提醒
         * 行业
         * 金融业 - 证券|基金|理财|信托
         * 详细内容
         * {{first.DATA}}
         * 产品名称：{{keyword1.DATA}}
         * 当期兑付本金：{{keyword2.DATA}}
         * 当期兑付利息：{{keyword3.DATA}}
         * 已兑付期数：{{keyword4.DATA}}
         * 兑付日期：{{keyword5.DATA}}
         * {{remark.DATA}}
         */
        sendMag.put("first", new WeChatTemplateMsgDTO("f123"));
        sendMag.put("keyword1", new WeChatTemplateMsgDTO("111"));
        sendMag.put("keyword2", new WeChatTemplateMsgDTO("222"));
        sendMag.put("keyword3", new WeChatTemplateMsgDTO("333"));
        sendMag.put("keyword4", new WeChatTemplateMsgDTO("444"));
        sendMag.put("remark", new WeChatTemplateMsgDTO("r555"));
        RestTemplate restTemplate = new RestTemplate();
        //拼接base参数
        Map<String, Object> sendBody = new HashMap<>();
        sendBody.put("touser", openId);               // openId
        sendBody.put("url", "www.baidu.com");         // 点击模板信息跳转地址
        sendBody.put("topcolor", "#FF0000");          // 顶色
        sendBody.put("data", sendMag);                   // 模板参数
        sendBody.put("template_id", templateId);      // 模板Id
        ResponseEntity<String> forEntity = restTemplate.postForEntity(url, sendBody, String.class);
        JSONObject jsonObject = JSONObject.parseObject(forEntity.getBody());
        // 0
        String messageCode = jsonObject.getString("errcode");
        // 2431260672639467520
        String msgId = jsonObject.getString("msgid");
        System.out.println("messageCode : " + messageCode + ", msgId: " +msgId);
        return forEntity.getBody();
    }
}
