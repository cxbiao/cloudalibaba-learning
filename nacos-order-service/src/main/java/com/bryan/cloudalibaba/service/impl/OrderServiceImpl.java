package com.bryan.cloudalibaba.service.impl;

import com.bryan.cloudalibaba.mapper.OrderMapper;
import com.bryan.cloudalibaba.pojo.Order;
import com.bryan.cloudalibaba.service.OrderService;
import io.seata.core.context.RootContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;

    @Override
    //@Transactional(rollbackFor = Exception.class)  不用加
    public int insertOrder(Order order) {
        log.info("Order Service Begin ... xid: " + RootContext.getXID());
        int ret=orderMapper.insert(order);
        log.info("Order Service end ... : " + order);
        if("order".equals(order.getGoods())){
            throw new IllegalArgumentException("order");
        }
        return ret;
    }
}
