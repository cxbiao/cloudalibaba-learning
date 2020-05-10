package com.bryan.cloudalibaba;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class RateConfigration {
    /**
     * 按照Path限流
     *这样，限流规则即可作用在路径上
     *
     * 例如：
     * # 访问：http://${GATEWAY_URL}/users/1，对于这个路径，它的redis-rate-limiter.replenishRate = 1，redis-rate-limiter.burstCapacity = 2；
     * # 访问：http://${GATEWAY_URL}/shares/1，对这个路径，它的redis-rate-limiter.replenishRate = 1，redis-rate-limiter.burstCapacity = 2；
     *
     *  超过数量 Response code:429
     * Response message:Too Many Requests
     * @return key
     */
    @Bean
    public KeyResolver pathKeyResolver() {
        return exchange -> Mono.just(
                exchange.getRequest()
                        .getPath()
                        .toString()
        );
    }

    /**
     * 实现针对用户的限流
     *
     * @return
     */
    //@Bean
    public KeyResolver userKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("user"));
    }


    /**
     * 针对来源IP的限流：
     *
     * @return
     */
    //@Bean
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(
                exchange.getRequest()
                        .getHeaders()
                        .getFirst("X-Forwarded-For")
        );
    }


}
