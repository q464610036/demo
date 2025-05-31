package com.example.springbootrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;


@Configuration
public class RabbitMQConsumerConfig {
    // 邮件队列名
    public static final String QUEUE_INFORM_EMAIL = "queue_inform_email";
    // 短信队列名
    public static final String QUEUE_INFORM_SMS = "queue_inform_sms";
    // 交换机名
    public static final String EXCHANGE_TOPICS_INFORM="exchange_topics_inform";
    // 定义邮件通知的路由键模式，使用通配符#以匹配多个单词
    public static final String ROUTINGKEY_EMAIL="inform.#.email.#";
    // 定义短信通知的路由键模式，使用通配符#以匹配多个单词
    public static final String ROUTINGKEY_SMS="inform.#.sms.#";


    //声明QUEUE_INFORM_EMAIL队列
    @Bean(QUEUE_INFORM_EMAIL)
    public Queue QUEUE_INFORM_EMAIL(){
        return new Queue(QUEUE_INFORM_EMAIL, true);
    }
    //声明QUEUE_INFORM_SMS队列
    @Bean(QUEUE_INFORM_SMS)
    public Queue QUEUE_INFORM_SMS(){
        return new Queue(QUEUE_INFORM_SMS, true);
    }

    //ROUTINGKEY_EMAIL队列绑定交换机，指定routingKey
    @Bean
    public Binding bindingEmail(@Qualifier(QUEUE_INFORM_EMAIL) Queue queue,
                                              @Qualifier(EXCHANGE_TOPICS_INFORM) Exchange exchange){
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(ROUTINGKEY_EMAIL).noargs();
    }
    //ROUTINGKEY_SMS队列绑定交换机，指定routingKey
    @Bean
    public Binding bindingSms(@Qualifier(QUEUE_INFORM_SMS) Queue queue,
                                          @Qualifier(EXCHANGE_TOPICS_INFORM) Exchange exchange){
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(ROUTINGKEY_SMS).noargs();
    }

    //允许列表类反序列化配置
    @Bean
    public List<String> allowedClasses(){
        return Arrays.asList(
                "com.example.springbootrabbitmq.vo.Order"
        );
    }

}
