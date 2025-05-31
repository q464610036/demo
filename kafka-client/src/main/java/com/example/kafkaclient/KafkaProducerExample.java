package com.example.kafkaclient;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.record.CompressionType;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.UUID;

public class KafkaProducerExample  {
    public static void main(String[] args) {
        // 配置生产者属性
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");  // Kafka Broker 地址
        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG,
                "com.example.kafkaclient.interceptor.MyMyProducerInterceptor"); //生产者消息拦截
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); // 键序列化
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); // 值序列化
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "org.apache.kafka.clients.producer.RoundRobinPartitioner");//轮询分区策略

        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG,33554432);//缓冲区大小(这里先设置为32MB)
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG,16384);//批次大小(这里先设置为16K)
        properties.put(ProducerConfig.ACKS_CONFIG,1);//发送应答配置
        properties.put(ProducerConfig.RETRIES_CONFIG, 10); //消息发送失败重试次数，默认是MAX_Integer
        properties.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, 300); //消息发送失败重试间隔，单位毫秒
        properties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);//是否启用生产者幂等
        properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, CompressionType.GZIP);//压缩算法
        properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, UUID.randomUUID());

        // 创建 KafkaProducer 实例
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // 发送消息
        String topic = "test-topic";
        String key = "key1";
        String value = "Hello, Kafka from Java!";

        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);

        try {
            //开启事务
//            producer.beginTransaction();
            //单项发送，不关心服务器应答
            producer.send(record);
            //同步发送，获取获取服务应答前会阻塞，这个可以解决可靠消息问题
            RecordMetadata recordMetadata = producer.send(record).get();
            //offset，消费消息偏移量，可以知道消费了多少条
            System.out.println("Sent message to topic: " + recordMetadata.topic() + " with offset: " + recordMetadata.offset());
            // 异步发送，主线程不阻塞
            producer.send(record, (metadata, exception) -> {
                if (exception != null) {
                    exception.printStackTrace();
                } else {
                    System.out.println("Sent message to topic: " + metadata.topic() + " with offset: " + metadata.offset());
                }
            });
            //提交事务
//            producer.commitTransaction();
        } catch (Exception e) {
            //回滚
//            producer.abortTransaction();
            e.printStackTrace();
        } finally {
            producer.close();  // 关闭生产者
        }
    }
}

