package com.bryan.cloudalibaba.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Component;

@Component
public class ResourceService {

    @SentinelResource("xx")
    public String xxx(){
        return "xx";
    }
}
