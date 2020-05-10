package com.bryan.cloudalibaba.service.impl;

import com.bryan.cloudalibaba.feignclient.LoginFeignClient;
import com.bryan.cloudalibaba.pojo.ApiResponse;
import com.bryan.cloudalibaba.pojo.User;
import com.bryan.cloudalibaba.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginFeignService implements LoginService {


    @Autowired
    private LoginFeignClient loginFeignClient;

    @Override
    public ApiResponse loginByUsername(String username, String password) {
        return loginFeignClient.loginByUsername(username,password);
    }

    @Override
    public ApiResponse loginByBody(User user) {
        return loginFeignClient.loginByBody(user);
    }

    @Override
    public ApiResponse loginByPath(String username, String password) {
        return loginFeignClient.loginByPath(username,password);
    }

    @Override
    public ApiResponse loginByPojo(User user) {
        return loginFeignClient.loginByPojo(user);
    }
}
