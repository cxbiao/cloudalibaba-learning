package com.bryan.cloudalibaba.sentinel;


import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.fastjson.JSON;
import com.bryan.cloudalibaba.pojo.ApiResponse;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 只能处理BlockException
 * 只能处理RequestMapping这种资源
 */
@Component
public class MyUrlBlockHandler implements BlockExceptionHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        String msg=e.getMessage();
        if (e instanceof FlowException) {
            msg= JSON.toJSONString(ApiResponse.failure(100,"限流了"));
        } else if (e instanceof DegradeException) {
            msg= JSON.toJSONString(ApiResponse.failure(101,"降级了"));
        } else if (e instanceof ParamFlowException) {
            msg= JSON.toJSONString(ApiResponse.failure(102,"热点参数限流"));
        } else if (e instanceof SystemBlockException) {
            msg= JSON.toJSONString(ApiResponse.failure(103,"系统规则（负载/...不满足要求）"));
        } else if (e instanceof AuthorityException) {
            msg= JSON.toJSONString(ApiResponse.failure(104,"授权规则不通过"));
        }
        // http状态码
        response.setStatus(500);
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(msg);
    }
}


