package com.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    public static void main(String[] args) throws IOException {
        // 1. 创建 ServerSocketChannel 并绑定端口
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        serverSocketChannel.configureBlocking(false); // 非阻塞模式

        // 2. 创建 Selector 并注册 ACCEPT 事件
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("NIO 服务端启动，端口: 8080");

        while (true) {
            // 3. 阻塞等待就绪的事件（可设置超时），当select检查完一遍fd，返现没有就绪的fd，就会阻塞用户进程。
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

            // 4. select不在阻塞，说明有数据就绪。用户应用遍历所有就绪的事件，遍历所有的就绪fd
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                keyIterator.remove(); // 必须移除已处理的键

                // 处理连接事件
                if (key.isAcceptable()) {
                    //根据不同的fd找到不同的客户端通道，对数据进行处理。
                    ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
                    SocketChannel clientChannel = serverChannel.accept();
                    clientChannel.configureBlocking(false);
                    clientChannel.register(selector, SelectionKey.OP_READ); // 注册读事件
                    System.out.println("客户端连接: " + clientChannel.getRemoteAddress());
                }

                // 处理读事件
                if (key.isReadable()) {
                    SocketChannel clientChannel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    try {
                        int bytesRead = clientChannel.read(buffer);
                        if (bytesRead > 0) {
                            buffer.flip();
                            String request = new String(buffer.array(), 0, bytesRead).trim();
                            System.out.println("收到客户端消息: " + request);

                            // 向客户端写响应
                            String response = "服务端响应: " + request.toUpperCase();
                            ByteBuffer responseBuffer = ByteBuffer.wrap(response.getBytes());
                            clientChannel.write(responseBuffer);
                        } else if (bytesRead == -1) { // 客户端断开连接
                            System.out.println("客户端断开: " + clientChannel.getRemoteAddress());
                            key.cancel();
                            clientChannel.close();
                        }
                    } catch (IOException e) {
                        key.cancel();
                        clientChannel.close();
                    }
                }
            }
        }
    }
}