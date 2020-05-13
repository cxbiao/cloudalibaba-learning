package com.bryan.cloudalibaba.feignclient;

import com.bryan.cloudalibaba.pojo.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(name = "order-service",contextId = "x2")
public interface OrderFeignClient {

    @PostMapping("/order/add")
    ApiResponse addOrder(@RequestParam String goods, @RequestParam Integer count,@RequestParam BigDecimal money);
}
