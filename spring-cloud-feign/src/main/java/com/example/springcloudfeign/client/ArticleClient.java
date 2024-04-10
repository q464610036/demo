package com.example.springcloudfeign.client;

import com.example.springcloudfeign.client.fallback.ArticleFallBackFactory;
import com.example.springcloudfeign.config.LoadBalancerConfig;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "article", fallbackFactory = ArticleFallBackFactory.class)
//指定负载均衡策略为随机，用了这个就不用ribbon
@LoadBalancerClient(value = "article", configuration = LoadBalancerConfig.class)
public interface ArticleClient {
    //@LoadBalanced(可以写，也可以不用写，默认所有方法都自动加 @LoadBalanced)
    @HystrixCommand(commandProperties = {
            //隔离
            //信号量隔离
            @HystrixProperty(name= HystrixPropertiesManager.EXECUTION_ISOLATION_STRATEGY, value = "SEMAPHORE"),
            //信号量最大并发
            @HystrixProperty(name= HystrixPropertiesManager.EXECUTION_ISOLATION_SEMAPHORE_MAX_CONCURRENT_REQUESTS, value = "10"),

            //熔断
            //条件1：一个时间窗口，请求数量到达3
            @HystrixProperty(name= HystrixPropertiesManager.CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD, value = "3"),
            //10秒为一个时间窗口
            @HystrixProperty(name= HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "10"),
            //条件2：失败率到达50%
            @HystrixProperty(name= HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE, value = "50"),
            //满足熔断条件的结果：开启熔断后，30秒不在访问远程服务器
            @HystrixProperty(name= HystrixPropertiesManager.CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS, value = "30000"),
    })
    //请求合并
    @HystrixCollapser(batchMethod = "getOneBatch", scope = com.netflix.hystrix.HystrixCollapser.Scope.GLOBAL,
            collapserProperties = {
                    //间隔多久会请求合并，默认10秒
                    @HystrixProperty(name=HystrixPropertiesManager.TIMER_DELAY_IN_MILLISECONDS, value = "20"),
                    //允许的最大请求数
                    @HystrixProperty(name = HystrixPropertiesManager.MAX_REQUESTS_IN_BATCH, value = "200")
            }

    )
    @GetMapping("/article/getOne")
    String getOne(Long id);

    @GetMapping("/article/getOneBatch")
    List<String> getOneBatch(List<Long> ids);
}