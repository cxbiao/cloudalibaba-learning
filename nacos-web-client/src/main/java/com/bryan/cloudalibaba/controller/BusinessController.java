package com.bryan.cloudalibaba.controller;

import com.bryan.cloudalibaba.pojo.ApiResponse;
import com.bryan.cloudalibaba.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class BusinessController {

    @Autowired
    private BusinessService businessService;


    @PostMapping("/business")
    public ApiResponse business(@RequestParam String goods, @RequestParam Integer count, @RequestParam BigDecimal money){
        return businessService.addOrder(goods,count,money);
    }

}
