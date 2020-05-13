package com.bryan.cloudalibaba.controller;

import com.bryan.cloudalibaba.pojo.ApiResponse;
import com.bryan.cloudalibaba.pojo.Order;
import com.bryan.cloudalibaba.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RequestMapping("/order")
@RestController
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    public ApiResponse insertOrder(String goods, Integer count, BigDecimal money){
        Order order=Order.builder().goods(goods).count(count).money(money).build();
        orderService.insertOrder(order);
        return ApiResponse.success("orderId="+order.getId());

    }

}
