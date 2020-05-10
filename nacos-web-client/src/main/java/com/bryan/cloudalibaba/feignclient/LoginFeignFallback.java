package com.bryan.cloudalibaba.feignclient;

import com.bryan.cloudalibaba.pojo.ApiResponse;
import com.bryan.cloudalibaba.pojo.User;
import org.springframework.stereotype.Component;

@Component
public class LoginFeignFallback implements LoginFeignClient{
    @Override
    public ApiResponse loginByUsername(String username, String password) {
        return ApiResponse.failure(-1,"error:loginByUsername=>"+username+":"+password);
    }

    @Override
    public ApiResponse loginByBody(User user) {
        return ApiResponse.failure(-1,"error:loginByBody=>"+user.toString());

    }

    @Override
    public ApiResponse loginByPath(String username, String password) {
        return ApiResponse.failure(-1,"error:loginByPath=>"+username+":"+password);

    }

    @Override
    public ApiResponse loginByPojo(User user) {
        return ApiResponse.failure(-1,"error:loginByPojo=>"+user.toString());
    }
}
