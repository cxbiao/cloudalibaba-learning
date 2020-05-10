package com.bryan.cloudalibaba.config;


import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;
import ribbon.config.RibbonLoadBalanceOrderConfig;

/*
* 这个是针对 micro-order服务的 ribbon配置
* */
@Configuration
@RibbonClient(name = "order-service",configuration = RibbonLoadBalanceOrderConfig.class)
//全局配置
//@RibbonClients(defaultConfiguration = RibbonLoadBalanceOrderConfig.class)
public class LoadBalanceConfig {

}
