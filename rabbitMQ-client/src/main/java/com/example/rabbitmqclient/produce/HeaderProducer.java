package com.example.rabbitmqclient.produce;


import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

//生产者
public class HeaderProducer {

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
        channel.basicQos(prefetchCount);

        //头交换机
        Map<String, Object> headers = new HashMap<>();
        headers.put("condition1", "order");
        headers.put("condition2", "system");
        AMQP.BasicProperties.Builder properties = new AMQP.BasicProperties().builder().headers(headers);
        // 要发送的信息
        String message = "你好；小兔子111！";
        channel.basicPublish("simple_exchange", "", true, false,
                properties.build(), message.getBytes());
        // 关闭资源
        channel.close();
        connection.close();
    }
}
