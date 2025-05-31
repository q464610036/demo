package com.example.kafkaclient;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.Properties;

public class KafkaConsumerExample {
    public static void main(String[] args) {
        // 配置消费者属性
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");  // Kafka Broker 地址
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group");  // 消费者组ID
        properties.put(ConsumerConfig.GROUP_INSTANCE_ID_CONFIG, "1"); //组内唯一固定id
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName()); // 键反序列化
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName()); // 值反序列化
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false); //是否自动提交offset
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // 自动重置消费偏移量的策略（latest/earliest）
        properties.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, "org.apache.kafka.clients.consumer.RangeAssignor"); //消息路由
        // 创建 KafkaConsumer 实例
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        // 订阅主题
        consumer.subscribe(Collections.singletonList("test-topic"));
        // 消费消息
        try {
            while (true) {
                // 拉取消息
                var records = consumer.poll(1000);  // 每次最多阻塞 1 秒钟
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println("Consumed record: " + record.value() + " from partition " + record.partition());
                }
                consumer.commitSync(); //同步提交，必须等offset提交完毕才会消费下一批
//                consumer.commitAsync();//异步提交，表示提交完offset后就开始处理下一批消息了，不用等到broker的确认
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();  // 关闭消费者
        }
    }

    private void init(){
        System.out.println("-----init-----");
    }
}
