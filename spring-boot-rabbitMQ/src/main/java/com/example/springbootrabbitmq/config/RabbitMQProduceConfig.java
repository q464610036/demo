package com.example.springbootrabbitmq.config;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQProduceConfig {
    // 交换机名
    public static final String EXCHANGE_TOPICS_INFORM="exchange_topics_inform";

    //声明交换机
    @Bean(EXCHANGE_TOPICS_INFORM)
    public Exchange EXCHANGE_TOPICS_INFORM(){
        return ExchangeBuilder
                .topicExchange(EXCHANGE_TOPICS_INFORM)
                .durable(true)
//                .autoDelete()
                .build();
    }

    //序列化
    @Bean
    public MessageConverter jsonMessageConverter() {
        Jackson2JsonMessageConverter messageConverter = new Jackson2JsonMessageConverter();
        //是否生成消息id
        messageConverter.setCreateMessageIds(true);
        return messageConverter;
    }

//    //主要是重写RabbitTemplate的序列化方法，改为我们之前定义的
//    @Bean
//    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
//                                         MessageConverter jsonMessageConverter) {
//        RabbitTemplate template = new RabbitTemplate(connectionFactory);
//        template.setMessageConverter(jsonMessageConverter);
//        return template;
//    }
}
