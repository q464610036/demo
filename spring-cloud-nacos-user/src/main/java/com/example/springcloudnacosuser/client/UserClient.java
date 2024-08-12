package com.example.springcloudnacosuser.client;

import com.example.springcloudnacosuser.client.fallback.UserFallBackFactory;
import com.example.springcloudnacosuser.config.LoadBalancerConfig;
import com.example.springcloudnacosuser.module.user.entity.User;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(
        value = "server-user",
        path = "/user",
        url = "${server-user.url:}",
        fallbackFactory = UserFallBackFactory.class)
/**
 * value：服务名（接口提供方的服务名）
 * path：定义当前FeignClient的统一路径前缀
 * name：指定FeignClient的名称，如果项目使用了Ribbon 或spring-cloud-loadbalancer，name属性会作为微服务的名称，用于服务发现
 * contextId：解决bean名冲突的标识
 * url：url一般用于调试，可以手动指定@FeignClient调用的地址
 * decode404：当发生http 404错误时，如果该字段位true，会调用decoder进行解码，否则抛出FeignException
 * configuration：Feign配置类，可以自定义Feign的Encoder、Decoder、LogLevel、Contract
 * fallback：定义容错的处理类，当调用远程接口失败或超时时，会调用对应接口的容错逻辑，fallback指定的类必须实现@FeignClient标记的接口
 * fallbackFactory：工厂类，用于生成fallback类示例，通过这个属性我们可以实现每个接口通用的容错逻辑，减少重复的代码
 * ————————————————
 *
 *                             版权声明：本文为博主原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接和本声明。
 *
 * 原文链接：https://blog.csdn.net/qq_42077317/article/details/137789425
 */
//@FeignClient(name ="a", url = "http://x.x.x.x:8080")
//指定负载均衡策略为随机，用了这个就不用ribbon
@LoadBalancerClient(value = "spring-cloud-feign-hystrix", configuration = LoadBalancerConfig.class)
public interface UserClient {
    @GetMapping("/user/getOne")
    User getOne(Integer id);

    @GetMapping("/user/getOneBatch")
    List<User> getOneBatch(List<Integer> ids);
}