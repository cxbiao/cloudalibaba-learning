package com.bryan.cloudalibaba.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebConfig {

    @Autowired
   private RestTemplateBuilder restTemplateBuilder;


    @Bean
    @LoadBalanced
    /**
     * 降级规则走fallback
     * 其他走blockHandler
     */
   // @SentinelRestTemplate(blockHandler = "restBlockMethod",blockHandlerClass = ExceptionUtils.class,fallback = "restExceptionMethod",fallbackClass =ExceptionUtils.class )
    public RestTemplate restTemplate(){
        return restTemplateBuilder.build();
    }

}
