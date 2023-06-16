package com.cn;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;

import java.io.IOException;
import java.net.UnknownHostException;

/**
 * 建立索引，增删改查
 */
public class Test1 {
    public static void main(String[] args) throws IOException {
        //创建es客户端
        RestHighLevelClient esClient = new RestHighLevelClient(RestClient.builder(new HttpHost("127.0.0.1", 9200)));
        System.out.println(esClient);

        //关闭客户端连接
//        esClient.close();


    }
}
