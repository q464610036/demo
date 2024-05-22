package com.example.springcloudgateway.factory.fillter;

import com.example.springcloudgateway.factory.predicate.MyRoutePredicateFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.RedirectToGatewayFilterFactory;
import org.springframework.cloud.gateway.support.HttpStatusHolder;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotEmpty;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static org.springframework.cloud.gateway.support.GatewayToStringStyler.filterToStringCreator;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.setResponseStatus;

/**
 * 自定义过滤器工厂，可以照着AddRequestParameterGatewayFilterFactory写
 *
 * 为url增加参数
 */
@Component
public class MyGatewayFilterFactory extends AbstractGatewayFilterFactory<MyGatewayFilterFactory.Config> {

    //配置文件中自定义key
    public static final String NAME_KEY = "name";
    //配置文件中自定义value
    public static final String VALUE_KEY = "value";

    public MyGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(NAME_KEY, VALUE_KEY);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                URI uri = exchange.getRequest().getURI();
                StringBuilder query = new StringBuilder();
                String originalQuery = uri.getRawQuery();
                if (StringUtils.hasText(originalQuery)) {
                    query.append(originalQuery);
                    if (originalQuery.charAt(originalQuery.length() - 1) != '&') {
                        query.append('&');
                    }
                }
                String value = ServerWebExchangeUtils.expand(exchange, config.getValue());
                // TODO urlencode?
                query.append(config.getName());
                query.append('=');
                query.append(value);
                try {
                    URI newUri = UriComponentsBuilder.fromUri(uri).replaceQuery(query.toString()).build(true).toUri();
                    ServerHttpRequest request = exchange.getRequest().mutate().uri(newUri).build();
                    return chain.filter(exchange.mutate().request(request).build());
                }
                catch (RuntimeException ex) {
                    throw new IllegalStateException("Invalid URI query: \"" + query.toString() + "\"");
                }
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
