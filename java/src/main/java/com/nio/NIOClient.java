package com.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class NIOClient {
    public static void main(String[] args) throws IOException {
        // 1. 创建 SocketChannel 并连接服务端
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 8080));
        socketChannel.configureBlocking(false); // 非阻塞模式
        System.out.println("客户端连接服务端成功");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("请输入消息: ");
            String input = scanner.nextLine();
            if ("exit".equalsIgnoreCase(input)) break;

            // 2. 向服务端发送数据
            ByteBuffer writeBuffer = ByteBuffer.wrap(input.getBytes());
            socketChannel.write(writeBuffer);

            // 3. 非阻塞读取服务端响应
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            while (socketChannel.read(readBuffer) == 0) {
                // 无数据可读时，短暂等待（非阻塞模式下直接继续循环）
                try { Thread.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }
            }
            readBuffer.flip();
            String response = new String(readBuffer.array(), 0, readBuffer.limit());
            System.out.println("服务端响应: " + response);
        }

        socketChannel.close();
    }
}