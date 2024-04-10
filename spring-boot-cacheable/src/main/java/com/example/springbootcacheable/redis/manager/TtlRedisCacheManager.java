package com.example.springbootcacheable.redis.manager;


import lombok.NonNull;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.util.StringUtils;

import java.time.Duration;

public class TtlRedisCacheManager extends RedisCacheManager {

    public final static Long DEFAULT_REDIS_CACHE_TTL = 60L;
    public TtlRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration);
    }

    @Override
    protected RedisCache createRedisCache(@NonNull String name, RedisCacheConfiguration cacheConfig) {
        // 这里的name，就是@Cacheable注解的 value或者cacheNames值
        String[] cells = StringUtils.delimitedListToStringArray(name, "=");
        String redisKey = cells[0];
        if (cells.length > 1) {
            // 如果有设置，比如@Cacheable (value = "user=30", key = "#id"), 那么30就是过期时间
            long ttl = Long.parseLong(cells[1]);
            cacheConfig = cacheConfig.entryTtl(Duration.ofSeconds(ttl));
        }else{
            // 默认1分钟
            cacheConfig = cacheConfig.entryTtl(Duration.ofSeconds(DEFAULT_REDIS_CACHE_TTL));
        }
        return super.createRedisCache(redisKey, cacheConfig);
    }
}


