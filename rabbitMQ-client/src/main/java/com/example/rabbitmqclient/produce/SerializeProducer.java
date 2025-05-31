package com.example.rabbitmqclient.produce;

import com.rabbitmq.client.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

//生产者
public class SerializeProducer {

    public static Connection getConnection() throws Exception {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //主机地址  如果是本机就是localhost   如果在其他 地方比如:虚拟机中 那么就是ip地址
        connectionFactory.setHost("192.168.216.128");
        //连接端口;默认为 5672
        connectionFactory.setPort(5672);
        //虚拟主机名称   就是和你用户绑定的虚拟机  在创建用户时候就指定了
        connectionFactory.setVirtualHost("/itcast");
        //连接用户名
        connectionFactory.setUsername("admin");
        //连接密码
        connectionFactory.setPassword("admin");
        //创建连接
        return connectionFactory.newConnection();
    }

    public static void main(String[] args) throws Exception {
        //创建连接
        Connection connection = getConnection();
        // 创建频道
        Channel channel = connection.createChannel();
        int prefetchCount = 1;
        channel.basicQos(prefetchCount); //kafka手打消费者的ack前可发送的消息数
        channel.confirmSelect(); //发送者确认机制

        channel.exchangeDeclare("simple_exchange", BuiltinExchangeType.TOPIC,
                false, true, false, null);


        //绑定交换机
        channel.queueBind("simple_queue", "simple_exchange", "key1");

        // 要发送的信息
        Map<String, Object> messageMap = new HashMap<>();
        messageMap.put("name", "张三");
        messageMap.put("age", 1);
        //java对象序列化：spring boot amqp也是这么是实现的
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ObjectOutputStream objectStream = new ObjectOutputStream(stream);
        objectStream.writeObject(messageMap);
        channel.basicPublish("", "simple_queue", MessageProperties.PERSISTENT_BASIC, stream.toByteArray());

        // 关闭资源
        channel.close();
        connection.close();
    }
}
