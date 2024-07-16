package com.example.elasticsearchspringboot.util;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EsUtil {

    @Autowired
    private RestHighLevelClient elasticsearchClient;
    /**
     * 创建索引
     * @throws IOException
     */
    public void createIndex() throws IOException {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("products");
        //指定映射 参数1：指定映射json结构 参数2：指定数据类型
        createIndexRequest.mapping("{\n" +
                "    \"properties\": {\n" +
                "        \"id\":{\n" +
                "          \"type\":\"integer\"\n" +
                "        },\n" +
                "        \"title\":{\n" +
                "          \"type\":\"keyword\"\n" +
                "        },\n" +
                "        \"price\":{\n" +
                "          \"type\":\"double\"\n" +
                "        },\n" +
                "        \"created_at\":{\n" +
                "          \"type\":\"date\"\n" +
                "        },\n" +
                "        \"description\":{\n" +
                "          \"type\":\"text\",\n" +
                "          \"analyzer\": \"ik_max_word\"\n" +
                "        }\n" +
                "    }\n" +
                "  }", XContentType.JSON);
        //参数1：创建索引请求对象  参数2：请求配置对象
        CreateIndexResponse createIndexResponse = elasticsearchClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        System.out.println("创建状态："+createIndexResponse.isAcknowledged()); //打印返回信息
//        elasticsearchClient.close(); //关闭资源
    }

    /**
     * 删除索引
     * @throws IOException
     */
    public void deleteIndex() throws IOException {
        AcknowledgedResponse acknowledgedResponse = elasticsearchClient.indices().delete(new DeleteIndexRequest("products"), RequestOptions.DEFAULT);
        System.out.println("删除状态：" + acknowledgedResponse.isAcknowledged()); //打印返回信息
//        elasticsearchClient.close(); //关闭资源
    }



}
