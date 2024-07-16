package com.example.elasticsearchspringboot;

import com.example.elasticsearchspringboot.util.EsDocumentUtil;
import com.example.elasticsearchspringboot.util.EsUtil;
import org.elasticsearch.action.get.GetResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class ElasticSearchSpringbootApplicationTests {
    @Autowired
    private EsUtil esUtil;

    @Autowired
    private EsDocumentUtil esDocumentUtil;

    @Test
    void createIndex() throws IOException {
        esUtil.createIndex();
    }

    @Test
    void createDoc() throws IOException {
        esDocumentUtil.createDoc();
    }

    @Test
    void searchDocById() throws IOException {
        GetResponse getResponse = esDocumentUtil.searchDocById(2L);
        System.out.println(getResponse.getId());
        System.out.println(getResponse.getSourceAsString());
    }
}
