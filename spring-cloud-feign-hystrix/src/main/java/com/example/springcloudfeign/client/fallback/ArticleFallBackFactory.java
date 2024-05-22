package com.example.springcloudfeign.client.fallback;

import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * FallBack只能托底，但是无法获取具体的错误信息，所以还需要FallBackFactory获取throwable，记录到日志。
 */
@Component
public class ArticleFallBackFactory implements FallbackFactory<ArticleFallBack> {
    private Logger log = LoggerFactory.getLogger(ArticleFallBackFactory.class);

    @Override
    public ArticleFallBack create(Throwable throwable) {
        return new ArticleFallBack(){
            @Override
            public String getOne(Long id) {
                log.error("被降级了，请排查"+throwable);
                return super.getOne(id);
            }
            @Override
            public List<String> getOneBatch(List<Long> ids) {
                log.error("被降级了，请排查"+throwable);
                return super.getOneBatch(ids);
            }

        };
    }
}
