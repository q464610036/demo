package com.example.rabbitmqclient.consumer;

import com.rabbitmq.client.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

//消费者
public class Consumer {

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

        // 声明（创建）队列，一般是在produce创建，consumer也可以创建
        /**
         * 参数1：队列名称
         * 参数2：是否定义持久化队列
         * 参数3：是否独占本次连接
         * 参数4：是否在不使用的时候自动删除队列
         * 参数5：队列其它参数
         */
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", "simple_dead_exchange");
        channel.queueDeclare("simple_queue", true, false, false, arguments);

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

        //监听消息（推模式，监听多个）
//        channel.basicConsume("simple_queue", false, new DeliverCallback() {
//                    @Override
//                    public void handle(String consumerTag, Delivery message) throws IOException {
//                        System.out.println("路由key为：" + message.getEnvelope().getRoutingKey());
//                        System.out.println("交换机为：" + message.getEnvelope().getExchange());
//                        System.out.println("消息id为：" + message.getEnvelope().getDeliveryTag());
//                        System.out.println("接收到的消息为：" + new String(message.getBody(), StandardCharsets.UTF_8));
//                        channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
//                    }
//                }
//                , new CancelCallback() {
//                    @Override
//                    public void handle(String consumerTag) throws IOException {
//                        //消息删除触发逻辑
//                    }
//                }, new ConsumerShutdownSignalCallback() {
//                    @Override
//                    public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
//                        //连接断开触发逻辑
//                    }
//                });

        //消费消息（拉模式）
//        GetResponse response = channel.basicGet("simple_queue", false);
//        byte[] body = response.getBody();
//        System.out.println("接收到的消息为：" + new String(body, StandardCharsets.UTF_8));
//        System.out.println("路由key为：" + response.getEnvelope().getRoutingKey());
//        System.out.println("交换机为：" + response.getEnvelope().getExchange());
//        System.out.println("消息id为：" + response.getEnvelope().getDeliveryTag());
//        channel.basicAck(response.getEnvelope().getDeliveryTag(), false);
        //push不关闭资源，应该一直监听消息。pull模式需要关闭
        //channel.close();
        //connection.close();
    }
}