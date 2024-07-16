package com.example.elasticsearchspringboot.util;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class EsDocumentUtil {

    @Autowired
    private RestHighLevelClient elasticsearchClient;

    /**
     * 创建文档
     * @throws IOException
     */
    public void createDoc() throws IOException {
        IndexRequest indexRequest = new IndexRequest("products");
        indexRequest.id("2") //手动指定文档的id，如果不指定则会用uuid
                .source("{\n" +
                        "  \"title\":\"瑞星咖啡\",\n" +
                        "  \"price\": 9.8,\n" +
                        "  \"created_at\":\"2023-11-18\",\n" +
                        "  \"description\":\"瑞星咖啡我最爱了，好喝\"\n" +
                        "}", XContentType.JSON);
        //参数1：索引请求对象，参数2：请求配置对象
        IndexResponse indexResponse = elasticsearchClient.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(indexResponse.status());
//        elasticsearchClient.close();
    }

    /**
     * 修改文档
     * @throws IOException
     */
    public void updateDoc() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("products","1");
        updateRequest.doc("{\n" +
                "    \"title\":\"库迪咖啡非\"\n" +
                "  }",XContentType.JSON);
        //参数1：索引请求对象，参数2：请求配置对象
        UpdateResponse updateResponse = elasticsearchClient.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(updateResponse.status());
//        elasticsearchClient.close();
    }

    /**
     * 删除文档
     * @throws IOException
     */
    public void deleteDoc() throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest("products","2");
        //参数1：索引请求对象，参数2：请求配置对象
        DeleteResponse deleteResponse = elasticsearchClient.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println(deleteResponse.status());
//        elasticsearchClient.close();
    }

    /**
     * 基于id查询文档
     * @throws IOException
     */
    public GetResponse searchDocById(Long id) throws IOException {
        GetRequest getRequest = new GetRequest("products", id.toString());
        GetResponse getResponse = elasticsearchClient.get(getRequest, RequestOptions.DEFAULT);
        return getResponse;
    }

    /**
     * 查询一个索引中的所有文档
     * @throws IOException
     */
    public void searchAll() throws IOException {
        SearchRequest searchRequest = new SearchRequest("products");
        searchRequest.source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()));//查询所有
        //参数1：搜索请求对象 参数2：请求配置对象
        SearchResponse searchResponse = elasticsearchClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println("查询出来的总条数："+searchResponse.getHits().getTotalHits().value);
        System.out.println("查询出来的最大得分"+searchResponse.getHits().getMaxScore());
        //拿到数据结果
        SearchHit[] hits = searchResponse.getHits().getHits();
        for(SearchHit hit:hits){
            String id = hit.getId();
            System.out.println("id: "+id+" source: "+hit.getSourceAsString());
        }
    }

    /**
     * 基于关键词查询
     * @throws IOException
     */
    public void search() throws IOException {
        SearchRequest searchRequest = new SearchRequest("products");
        searchRequest.source(new SearchSourceBuilder().query(QueryBuilders.termQuery("description","好喝")));//查询所有
        //参数1：搜索请求对象 参数2：请求配置对象
        SearchResponse searchResponse = elasticsearchClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println("查询出来的总条数："+searchResponse.getHits().getTotalHits().value);
        System.out.println("查询出来的最大得分"+searchResponse.getHits().getMaxScore());
        //拿到数据结果
        SearchHit[] hits = searchResponse.getHits().getHits();
        for(SearchHit hit:hits){
            String id = hit.getId();
            System.out.println("id: "+id+" source: "+hit.getSourceAsString());
        }
    }

    /*
     * 分页查询
     * 排序
     * 字段
     */
    public void search2() throws IOException {
        SearchRequest searchRequest = new SearchRequest("products");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery())
                .from(1) //起始位置 start=(page-1)*size
                .size(1) //每页显示条数
                .sort("price", SortOrder.ASC) //字段排序
                .fetchSource(new String[]{},new String[]{"created_at"}); //参数1：包含字段数组  参数2：排除字段数组
        searchRequest.source(searchSourceBuilder);//查询所有
        //参数1：搜索请求对象 参数2：请求配置对象
        SearchResponse searchResponse = elasticsearchClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println("查询出来的总条数："+searchResponse.getHits().getTotalHits().value);
        System.out.println("查询出来的最大得分"+searchResponse.getHits().getMaxScore());
        //拿到数据结果
        SearchHit[] hits = searchResponse.getHits().getHits();
        for(SearchHit hit:hits){
            String id = hit.getId();
            System.out.println("id: "+id+" source: "+hit.getSourceAsString());
        }
    }

    /**
     * 高亮
     * @throws IOException
     */
    public void search3() throws IOException {
        SearchRequest searchRequest = new SearchRequest("products");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.requireFieldMatch(false).field("description").field("title").preTags("<span style='color:red;'>").postTags("</span>");
        searchSourceBuilder.query(QueryBuilders.termQuery("description","好喝"))
                .highlighter(highlightBuilder);
        searchRequest.source(searchSourceBuilder);//查询所有
        //参数1：搜索请求对象 参数2：请求配置对象
        SearchResponse searchResponse = elasticsearchClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println("查询出来的总条数："+searchResponse.getHits().getTotalHits().value);
        System.out.println("查询出来的最大得分"+searchResponse.getHits().getMaxScore());
        //拿到数据结果
        SearchHit[] hits = searchResponse.getHits().getHits();
        for(SearchHit hit:hits){
            System.out.println("id: "+hit.getId()+" source: "+hit.getSourceAsString());
            //获取高亮字段
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            if(highlightFields.containsKey("description")){
                System.out.println("description高亮结果： "+highlightFields.get("description").fragments()[0]);
            }
            if(highlightFields.containsKey("title")){
                System.out.println("title高亮结果： "+highlightFields.get("title").fragments()[0]);
            }
        }
    }

    /**
     * 过滤查询
     * @throws IOException
     */
    public void search4() throws IOException {
        SearchRequest searchRequest = new SearchRequest("products");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery())
                .postFilter(QueryBuilders.idsQuery().addIds("1")); //指定过滤条件
        searchRequest.source(searchSourceBuilder);//查询所有
        //参数1：搜索请求对象 参数2：请求配置对象
        SearchResponse searchResponse = elasticsearchClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println("查询出来的总条数：" + searchResponse.getHits().getTotalHits().value);
        System.out.println("查询出来的最大得分" + searchResponse.getHits().getMaxScore());
        //拿到数据结果
        SearchHit[] hits = searchResponse.getHits().getHits();
        for (SearchHit hit : hits) {
            String id = hit.getId();
            System.out.println("id: " + id + " source: " + hit.getSourceAsString());
        }
    }
}
