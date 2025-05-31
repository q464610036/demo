package com.example.rabbitmqclient.consumer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

//消费者
public class DeadConsumer {

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
        channel.queueBind("simple_dead_queue", "simple_dead_exchange", "");

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
        //监听消息（推模式）
        /**
         * queue：队列名称
         * autoAck：是否自动确认，设置为true为表示消息接收到自动向mq回复接收到了，mq接收到回复会删除消息，设置为false则需要手动确认
         * callback：消息接收到后回调
         */
        channel.basicConsume("simple_queue", false, consumer);
    }
}