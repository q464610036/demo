package com.example.springcloudfeign.client;

import com.example.springcloudfeign.client.fallback.ArticleFallBackFactory;
import com.example.springcloudfeign.config.LoadBalancerConfig;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "spring-cloud-feign-hystrix", path = "/article", fallbackFactory = ArticleFallBackFactory.class)
//指定负载均衡策略为随机，用了这个就不用ribbon
@LoadBalancerClient(value = "spring-cloud-feign-hystrix", configuration = LoadBalancerConfig.class)
@DefaultProperties(groupKey = "DefaultGroupKey",
        commandProperties = {
                //设置隔离类型：SEMAPHORE=信号量隔离，THREAD=线程隔离
                @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_STRATEGY, value = "SEMAPHORE"),
                //信号量最大并发
                @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_SEMAPHORE_MAX_CONCURRENT_REQUESTS, value = "10"),
        }
)
public interface ArticleClient {
    //@LoadBalanced(可以写，也可以不用写，默认所有方法都自动加 @LoadBalanced)
    @HystrixCommand(
            //线程隔离
            /**
             * threadPoolKey：1：如果设置的groupKey值已经存在，他会使用这个groupKey值。
             *                  这种情况下：同一个groupKey下的依赖调用共用同一个线程池
             *                2：如果groupKey值不存在，则会对于这个groupKey新建一个线程池
             * 1：threadPoolKey的默认值是groupKey，而groupKey默认值是@HystrixCommand标注的方法所在类名
             * 2：可以通过在类上加@DefaultProperties(threadPoolKey="xxx")设置默认的threadPoolKey
             * 3；可以通过@HystrixCommand(threadPoolKey="xxx")指定当前HystrixCommand实例的threadPoolKey
             * 4：threadPoolKey用于从线程池缓存中获取线程池和初始化创建线程池，由于默认以groupKey即类名为threadPoolKey，
             * 那么默认所有在一个类中的HystrixCommand共用一个线程池
             * 5：动态配置线程池 --
             *  可以通过hystrix.command.HystrixCommandKey.threadPoolKeyOverride=线程池key动态设置
             *  threadPoolKey，对应的HystrixCommand所使用的线程池也会重新创建，还可以继续通过
             *  hystrix.threadpool.HystrixThreadPoolKey.coreSize=n和hystrix.threadpool.HystrixThreadPoolKey.maximumSize=n
             *  动态设置线程池大小
             * 6：commandKey的默认值是@HystrixCommand标注的方法名，即每个方法会被当做一个HystrixCommand
            */
            groupKey = "test",
            threadPoolKey = "time",
            //线程隔离
            threadPoolProperties = {
                @HystrixProperty(name = "coreSize", value = "20"),//线程池大小
                @HystrixProperty(name = "maximumSize", value = "30"),//最大线程池大小
                @HystrixProperty(name = "maxQueueSize", value = "20"),//最大队列长度
                @HystrixProperty(name = "keepAliveTimeMinutes", value = "2")//线程存活时间，单位分钟
            },
            commandProperties = {
                //设置隔离类型：SEMAPHORE=信号量隔离，THREAD=线程隔离
                @HystrixProperty(name= HystrixPropertiesManager.EXECUTION_ISOLATION_STRATEGY, value = "SEMAPHORE"),
                //信号量最大并发
                @HystrixProperty(name= HystrixPropertiesManager.EXECUTION_ISOLATION_SEMAPHORE_MAX_CONCURRENT_REQUESTS, value = "10"),

                //熔断
                //条件1：一个时间窗口，请求数量到达3
                @HystrixProperty(name= HystrixPropertiesManager.CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD, value = "3"),
                //10秒为一个时间窗口，单位毫秒
                @HystrixProperty(name= HystrixPropertiesManager.METRICS_ROLLING_STATS_TIME_IN_MILLISECONDS, value = "10000"),
                //条件2：失败率到达50%
                @HystrixProperty(name= HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE, value = "50"),
                //满足熔断条件的结果：开启熔断后，30秒不在访问远程服务器
                @HystrixProperty(name= HystrixPropertiesManager.CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS, value = "30000"),

                //超时
                @HystrixProperty(name= HystrixPropertiesManager.EXECUTION_TIMEOUT_ENABLED, value = "true"),
                //请求的超时时间，默认10000
                @HystrixProperty(name= HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "10000"),
                //当请求超时时，是否中断线程，默认true
                @HystrixProperty(name= HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_INTERRUPT_ON_TIMEOUT, value = "true"),
            }
    )
//    //请求合并
//    @HystrixCollapser(batchMethod = "getOneBatch", scope = com.netflix.hystrix.HystrixCollapser.Scope.GLOBAL,
//            collapserProperties = {
//                    //间隔多久会请求合并，默认10秒
//                    @HystrixProperty(name=HystrixPropertiesManager.TIMER_DELAY_IN_MILLISECONDS, value = "20"),
//                    //允许的最大请求数
//                    @HystrixProperty(name = HystrixPropertiesManager.MAX_REQUESTS_IN_BATCH, value = "200")
//            }
//
//    )
    @GetMapping("/getOne")
    String getOne(Long id);

    @GetMapping("/getOneBatch")
    List<String> getOneBatch(List<Long> ids);
}