package com.bryan.cloudalibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 调试技巧
 *1. 断点打在 org.springframework.cloud.gateway.filter.NettyRoutingFilter#filter ，就可以调试Gateway转发的具体细节了
 *2. actuator相应端点
 * 3.加日志，按需将如下包的日志级别设置成 debug 或 trace ，
 * org.springframework.cloud.gateway
 * org.springframework.http.server.reactive
 * org.springframework.web.reactive
 * org.springframework.boot.autoconfigure.web
 * reactor.netty
 * redisratelimiter
 *
 logging:
 level:
 org.springframework.cloud.gateway: trace
 */
@SpringBootApplication
public class GatewayApplication {

    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public Converter<String, LocalTime> localTimeConverter() {
        return  new Converter<String, LocalTime>() {
            @Override
            public LocalTime convert(String source) {
                return LocalTime.parse(source, DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT));
            }
        };
    }

}
