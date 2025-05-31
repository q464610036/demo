package com.example.kafkaclient;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
//import org.apache.kafka.streams.kstream.KStreams;

import java.util.Properties;

public class KafkaStreamsExample {
    public static void main(String[] args) {
        // 配置 Streams
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-streams-example");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

//        // 创建流
//        KStream<String, String> stream = KStreams.stream("input-topic");
//
//        // 数据流处理：将所有消息转换为大写
//        stream.mapValues(value -> value.toUpperCase()).to("output-topic");
//
//        // 启动 Kafka Streams 应用
//        KafkaStreams streams = new KafkaStreams(stream, properties);
//        streams.start();
    }
}
