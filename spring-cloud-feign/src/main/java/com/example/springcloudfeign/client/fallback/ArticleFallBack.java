package com.example.springcloudfeign.client.fallback;

import com.example.springcloudfeign.client.ArticleClient;
import org.springframework.stereotype.Component;

@Component
public class ArticleFallBack implements ArticleClient {
    /**
     * 服务降级策略
     * @return
     */
    @Override
    public String userStart() {
        return "aaaaaaa";
    }
}
