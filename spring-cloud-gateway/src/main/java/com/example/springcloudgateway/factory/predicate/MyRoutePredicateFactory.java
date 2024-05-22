package com.example.springcloudgateway.factory.predicate;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.cloud.gateway.handler.predicate.QueryRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import javax.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 自定义断言工厂，可以照着QueryRoutePredicateFactory.class去写
 *
 * 写一个url的param参数中，必须要包含某个入参，且入参的值也要与配置文件一致
 */
@Component
public class MyRoutePredicateFactory extends AbstractRoutePredicateFactory<MyRoutePredicateFactory.Config> {

    //配置文件中自定义key
    public static final String NAME_KEY = "name";
    //配置文件中自定义value
    public static final String VALUE_KEY = "value";

    public MyRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(NAME_KEY, VALUE_KEY);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return new GatewayPredicate() {
            @Override
            public boolean test(ServerWebExchange exchange) {
                //参数包含配置的name，直接拦截
                if (!exchange.getRequest().getQueryParams().containsKey(config.name)) {
                    return false;
                }
                //有保研配置的name，继续判断value是否相等
                List<String> values = exchange.getRequest().getQueryParams().get(config.name);
                if (values == null) {
                    return false;
                }
                for (String value : values) {
                    if (value != null && value.equals(config.getValue())) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

    @Validated
    public static class Config {
        @NotEmpty
        private String name;
        private String value;

        public Config() {
        }

        public String getName() {
            return this.name;
        }

        public Config setName(String name) {
            this.name = name;
            return this;
        }

        public String getValue() {
            return this.value;
        }

        public Config setValue(String value) {
            this.value = value;
            return this;
        }
    }
}
