package com.example.kafkaclient.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * 自定义生产者拦截器
 */
public class MyProducerInterceptor implements ProducerInterceptor {
    //发送消息时拦截
    @Override
    public ProducerRecord onSend(ProducerRecord record) {
        //对消息处理
        return record;
    }
    //收到服务端ACK响应拦截
    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        //通过RecordMetadata拿到分区，offset等
    }
    //关闭时拦截
    @Override
    public void close() {

    }
    //整理配置项时拦截
    @Override
    public void configure(Map<String, ?> configs) {
        for(String key : configs.keySet()) {
            //拿到所有配置型进行处理
        }
    }
}
