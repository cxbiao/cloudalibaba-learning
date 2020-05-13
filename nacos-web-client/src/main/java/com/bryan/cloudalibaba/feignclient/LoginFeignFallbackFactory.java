package com.bryan.cloudalibaba.feignclient;

import com.bryan.cloudalibaba.pojo.ApiResponse;
import com.bryan.cloudalibaba.pojo.User;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LoginFeignFallbackFactory implements FallbackFactory<LoginFeignClient> {
    @Override
    public LoginFeignClient create(Throwable throwable) {
        if(throwable == null) {
            return null;
        }
        log.error("出错了:"+throwable.getMessage(),throwable);
        //http status 都是200
        return new LoginFeignClient() {
            @Override
            public ApiResponse loginByUsername(String username, String password) {
                return ApiResponse.failure(-2,"error:loginByUsername=>"+username+":"+password);
            }

            @Override
            public ApiResponse loginByBody(User user) {
                return ApiResponse.failure(-2,"error:loginByBody=>"+user.toString());
            }

            @Override
            public ApiResponse loginByPath(String username, String password) {
                return ApiResponse.failure(-2,"error:loginByPath=>"+username+":"+password);
            }

            @Override
            public ApiResponse loginByPojo(User user) {
                return ApiResponse.failure(-2,"error:loginByPojo=>"+user.toString());
            }
        };
    }
}
