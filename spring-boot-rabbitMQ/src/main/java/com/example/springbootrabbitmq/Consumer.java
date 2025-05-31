package com.example.springbootrabbitmq;

import com.example.springbootrabbitmq.config.RabbitMQConsumerConfig;
import com.example.springbootrabbitmq.vo.Order;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
public class Consumer {
    //监听email队列
    @RabbitListener(queues = {RabbitMQConsumerConfig.QUEUE_INFORM_EMAIL})
    public void receive_email(Object msg, Message message){
        System.out.println("QUEUE_INFORM_EMAIL msg"+msg);
    }
    //监听sms队列
    @RabbitListener(queues = {RabbitMQConsumerConfig.QUEUE_INFORM_SMS})
    public void receive_sms(Order order, Message message){
        System.out.println("QUEUE_INFORM_SMS msg"+order);
    }

    //监听队列，原生channel方式，如果使用stream必须使用这种方式，因为其他方式没办法设置arguments
    @RabbitListener(queues = {RabbitMQConsumerConfig.QUEUE_INFORM_SMS})
    public void streamReceiver(Channel channel, Message message) throws IOException {
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
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-stream-offset", "last");
        channel.basicConsume(RabbitMQConsumerConfig.QUEUE_INFORM_SMS, false, arguments, consumer);
    }
}
