package com.example.springcloudfeign.client.fallback;

import com.example.springcloudfeign.client.ArticleClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleFallBack implements ArticleClient {
    /**
     * 服务降级策略
     * @return
     */
    @Override
    public String getOne(Long id) {
        return id+"降级了，托底数据";
    }

    @Override
    public List<String> getOneBatch(List<Long> ids) {
        List<String> list = new ArrayList<>();
        for (Long id : ids) {
            list.add(id+"降级了，托底数据");
        }
        return list;
    }
}
