package com.example.rabbitmqclient.produce;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

//生产者
public class Producer {

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
        // 声明（创建）队列
        /**
         * queue：队列名称
         * durable：是否定义持久化队列
         * exclusive：是否独占本次连接，如果设置为独占，只允许当前connection使用
         * autoDelete：是否在不使用的时候自动删除队列x
         * arguments：队列其它参数
         */

        Map<String, Object> arguments = new HashMap<>();
        //队列类型（非5中常见队列）：队列比交换机少了一个参数：type，队列也是有type的，只要在arguments设置
        // 队列有哪些类型要去rabbitMQ服务端后台创建队列的params去看。
//        arguments.put("x-", "stream");
//        arguments.put("x-delivery-limit", 5); //如果是仲裁队列，可限制没收到消费者ACK，消息重发次数
        arguments.put("x-queue-type", "quorum");
        channel.queueDeclare("simple_queue", true, false, false, arguments);

        //声明交换机
        /**
         * 如果服务端已经有同名交换机，那么再次声明交换机参数必须与之前一致，否则报错
         * exchange：交换机名称,
         * exchangeType：交换机类型,
         * durable：是否持久化，如果为false，下次重启rabbit就没了,
         * autoDelete：长时间没用了是否自动删除,
         * internal：在交换机这里设置为“Yes”之后，表示当前Exchange是RabbitMQ内部使用，用户所创建的Queue不会消费该类型交换机下的消息，既然是为了RabbitMQ系统所用,作为用户，我们就没有必要创建该类型的Exchange,当然默认也是选择No.
         * arguments：参数
         */
        channel.exchangeDeclare("simple_exchange", BuiltinExchangeType.TOPIC,
                false, true, false, null);


        //绑定交换机
        channel.queueBind("simple_queue", "simple_exchange", "key1");
        //服务端消息确认回调
        channel.addConfirmListener(new ConfirmCallback() { //ack确认成功
            @Override
            public void handle(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("消息id为：" + deliveryTag);
                System.out.println("是否批量确认：" + multiple);
            }
        }, new ConfirmCallback() { //ack确认失败，即nack
            @Override
            public void handle(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("消息id为：" + deliveryTag);
                System.out.println("是否批量确认：" + multiple);
            }
        });
        //消息返回时的回调
        channel.addReturnListener(new ReturnCallback() {
            @Override
            public void handle(Return returnMessage) {
                System.out.println("路由key为：" + returnMessage.getRoutingKey());
                System.out.println("交换机为：" + returnMessage.getExchange());
                System.out.println("接收到的消息为：" + new String(returnMessage.getBody(), StandardCharsets.UTF_8));
            }
        });


        // 要发送的信息
        String message = "你好；小兔子111！";
        /**
         * exchange：交换机名称，如果没有指定则使用默认Default Exchage
         * routingKey：路由key,简单模式可以传递队列名称
         * props：消息其它属性
         * body：消息内容
         */
//        channel.basicPublish("", "simple_queue", null, message.getBytes());
        channel.basicPublish("", "simple_queue", MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
//        channel.waitForConfirmsOrDie(5000); //发完消息等待5秒，没有收到回应抛出异常

        /**
         * exchange：交换机名称，如果没有指定则使用默认Default Exchage
         * routingKey：路由key,简单模式可以传递队列名称
         * mandatory：如果为true, 消息不能路由到指定的队列时，则会调用basic.return方法将消息返回给生产者,会触发addReturnListener注册的监听器；如果为false，则broker会直接将消息丢弃
         * immediate：:如果为true,当exchange将消息路由到queue时发现queue上没有消费者，那么这条消息不会放入队列中，该消息会通过basic.return方法返还给生产者。
         * props：消息其它属性
         * body：消息内容
         */
        channel.basicPublish("simple_exchange", "key1", true, false, MessageProperties.BASIC, message.getBytes());
//        channel.basicPublish("simple_exchange", "simple_queue", null, message.getBytes());
        // 关闭资源
        channel.close();
        connection.close();
    }
}
