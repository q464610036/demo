package com.example.springbootrabbitmq;
import com.example.springbootrabbitmq.config.RabbitMQProduceConfig;
import com.example.springbootrabbitmq.vo.Order;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class Producer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 初始化 RabbitTemplate 的回调配置
     * 该方法在Bean创建完成后由Spring框架调用，Bean实例化并完成依赖注入后，次方法会被自动调用
     */
    @PostConstruct
    public void init() {
        // 设置确认回调，处理消息是否到达交换机的确认信息
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            // 获取消息ID
            String id = null;
            if (correlationData != null) {
                id = correlationData.getId();
            }else {
                System.out.println("未找到消息ID");
                return;
            }
        });

        // 设置返回回调，处理消息未被路由到队列的情况
        rabbitTemplate.setReturnsCallback(returned -> {
            // 打印未被路由的消息信息
            System.out.println("消息未被路由到队列，返回内容：" + returned.getMessage() +
                    ", 回复代码: " + returned.getReplyCode() +
                    ", 回复文本: " + returned.getReplyText() +
                    ", 交换机: " + returned.getExchange() +
                    ", 路由键: " + returned.getRoutingKey());
        });
    }

    public void sent(Order order) {
        MessageProperties messageProperties = new MessageProperties();
        //指定消息数据类型，这里是byte
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_BYTES);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        // 发送对象消息
        rabbitTemplate.convertAndSend(RabbitMQProduceConfig.EXCHANGE_TOPICS_INFORM,
                "inform." + order.getUserId() + ".sms." + order.getOrderId(),
                order);
//        // 发送字符串消息
//        rabbitTemplate.send(RabbitMQProduceConfig.EXCHANGE_TOPICS_INFORM,
//                "inform." + order.getUserId() + ".sms." + order.getOrderId(),
//                new Message("消息内容".getBytes(StandardCharsets.UTF_8)));
    }


    public void sent1(Order order) {
        // 创建CorrelationData对象，用于关联消息和订单ID
        CorrelationData correlationData = new CorrelationData(order.getOrderId());
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());

        // 发送消息，包含消息确认和路由信息
        rabbitTemplate.convertAndSend(
                RabbitMQProduceConfig.EXCHANGE_TOPICS_INFORM,
                "inform." + order.getUserId() + ".sms." + order.getOrderId(),
                order,
                message -> {
                    // 设置消息的关联ID
                    message.getMessageProperties().setCorrelationId(order.getOrderId());
                    return message;
                },
                correlationData
        );
    }
}
