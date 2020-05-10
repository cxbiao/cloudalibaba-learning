package com.bryan.cloudalibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableFeignClients
//全局配置java代码
//@EnableFeignClients(defaultConfiguration = LoginFeignConfig.class)
public class NacosWebClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosWebClientApplication.class, args);
    }

}
