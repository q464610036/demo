package com.example.rabbitmqclient.consumer;

import com.rabbitmq.client.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

//消费者
public class SerializeConsumer {

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
        Connection connection = getConnection();

        // 创建频道
        Channel channel = connection.createChannel();

        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", "simple_dead_exchange");
        channel.queueDeclare("simple_queue", true, false, false, arguments);

        //创建消费者；并设置消息处理
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("路由key为：" + envelope.getRoutingKey());
                System.out.println("交换机为：" + envelope.getExchange());
                System.out.println("消费者ID：" + consumerTag);
                System.out.println("消息id为：" + envelope.getDeliveryTag());
                ByteArrayInputStream stream = new ByteArrayInputStream(body);
                ObjectInputStream objectStream = new ObjectInputStream(stream);
                try {
                    Map<String, Object> messageMap = (Map<String, Object>)objectStream.readObject();
                    System.out.println("接收到的消息为：" + messageMap);
                    channel.basicAck(envelope.getDeliveryTag(), false);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        };
        channel.basicConsume("simple_queue", false, consumer);

    }
}