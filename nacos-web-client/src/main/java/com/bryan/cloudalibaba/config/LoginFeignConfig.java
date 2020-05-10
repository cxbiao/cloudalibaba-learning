package com.bryan.cloudalibaba.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * feign的配置类
 * 这个类别加@Configuration注解了，否则必须挪到@ComponentScan能扫描的包以外
 */
public class LoginFeignConfig {

    @Bean
    public Logger.Level level(){
        // 让feign打印所有请求的细节
        return Logger.Level.FULL;
    }

    /**
     * 设置超时  属性文件试也可以配置
     * @return
     */
//    @Bean
//    public Request.Options options(){
//        return new Request.Options(2000,2000);
//    }
}
