package com.bryan.cloudalibaba.service;

import com.bryan.cloudalibaba.pojo.ApiResponse;
import com.bryan.cloudalibaba.pojo.User;

public interface LoginService {

   ApiResponse loginByUsername(String username, String password);
   ApiResponse loginByBody(User user);
   ApiResponse loginByPath(String username, String password);
   ApiResponse loginByPojo(User user);
}
