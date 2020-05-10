package com.bryan.cloudalibaba.service.impl;

import com.bryan.cloudalibaba.pojo.ApiResponse;
import com.bryan.cloudalibaba.pojo.User;
import com.bryan.cloudalibaba.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

//@Service
public class LoginServiceImpl implements LoginService {

    private static final String serviceName="order-service";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ApiResponse loginByUsername(String username, String password) {

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<String, String>();
        formData.add("username", username);
        formData.add("password", password);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        ApiResponse response = restTemplate.exchange("http://" + serviceName + "/login/loginByUsername", HttpMethod.POST,
                new HttpEntity(formData, headers), ApiResponse.class).getBody();

        return response;
    }

    @Override
    public ApiResponse loginByBody(User user) {
        ApiResponse response = restTemplate.postForEntity("http://" + serviceName + "/login/loginByBody", user, ApiResponse.class).getBody();
        return response;
    }

    @Override
    public ApiResponse loginByPath(String username, String password) {
        ApiResponse response = restTemplate.getForObject("http://" + serviceName + "/login/loginByPath/{user}/{pwd}", ApiResponse.class,username,password);
        return response;
    }

    @Override
    public ApiResponse loginByPojo(User user) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<String, String>();
        formData.add("username", user.getUsername());
        formData.add("password", user.getPassword());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        ApiResponse response = restTemplate.exchange("http://" + serviceName + "/login/loginByPojo", HttpMethod.POST,
                new HttpEntity(formData, headers), ApiResponse.class).getBody();

        return response;
    }
}
