package com.bryan.cloudalibaba.service.impl;

import com.bryan.cloudalibaba.feignclient.OrderFeignClient;
import com.bryan.cloudalibaba.mapper.AccountMapper;
import com.bryan.cloudalibaba.pojo.Account;
import com.bryan.cloudalibaba.pojo.ApiResponse;
import com.bryan.cloudalibaba.service.BusinessService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Slf4j
public class BusinessServiceImpl implements BusinessService {

    private final OrderFeignClient orderFeignClient;

    private final AccountMapper accountMapper;

    @Override
    @GlobalTransactional
    public ApiResponse addOrder(String goods, Integer count, BigDecimal money) {
        log.info("global tx Begin ... xid: " + RootContext.getXID());
        Account account=Account.builder().userId(1L).money(money).build();
        accountMapper.deduct(account);
        orderFeignClient.addOrder(goods,count,money);
        if("error".equals(goods)){
            throw new IllegalArgumentException("error");
        }
        return ApiResponse.success("add order success");
    }
}
