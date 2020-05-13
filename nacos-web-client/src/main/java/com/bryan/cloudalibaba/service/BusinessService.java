package com.bryan.cloudalibaba.service;

import com.bryan.cloudalibaba.pojo.ApiResponse;

import java.math.BigDecimal;

public interface BusinessService {
    ApiResponse addOrder( String goods,  Integer count,  BigDecimal money);
}
