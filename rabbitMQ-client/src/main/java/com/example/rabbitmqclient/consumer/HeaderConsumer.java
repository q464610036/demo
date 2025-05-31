package com.example.rabbitmqclient.consumer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

//消费者
public class HeaderConsumer {

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

        //创建消费者；并设置消息处理
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            /**
             * consumerTag 消息者标签，在channel.basicConsume时候可以指定
             * envelope 消息包的内容，可从中获取消息id，消息routingkey，交换机，消息和重传标志(收到消息失败后是否需要重新发送)
             * properties 属性信息
             * body 消息
             */
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("路由key为：" + envelope.getRoutingKey());
                System.out.println("交换机为：" + envelope.getExchange());
                System.out.println("消费者ID：" + consumerTag);
                System.out.println("消息id为：" + envelope.getDeliveryTag());
                System.out.println("接收到的消息为：" + new String(body, StandardCharsets.UTF_8));
                channel.basicAck(envelope.getDeliveryTag(), false);
//                channel.basicNack(envelope.getDeliveryTag(), false, true);
            }
        };

        //头交换机
        Map<String, Object> headers = new HashMap<>();
        headers.put("x-match", "all"); // any或all，any表示匹配一个即可，all表示完全匹配
        headers.put("condition1", "order");
        headers.put("condition2", "system");
        channel.exchangeDeclare("simple_exchange", BuiltinExchangeType.HEADERS,
                false, true, false, null);
        String queueName = channel.queueDeclare().getQueue();
        //连队列都不用创建，直接绑定队列名？
        channel.queueBind(queueName, "simple_exchange", "", headers); //header交换机routingKey没用
        channel.basicConsume(queueName, false, consumer);

    }
}