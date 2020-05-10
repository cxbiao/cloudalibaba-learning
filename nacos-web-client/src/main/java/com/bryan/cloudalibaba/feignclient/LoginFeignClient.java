package com.bryan.cloudalibaba.feignclient;


import com.bryan.cloudalibaba.config.LoginFeignConfig;
import com.bryan.cloudalibaba.pojo.ApiResponse;
import com.bryan.cloudalibaba.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

//细粒度配置java代码
@FeignClient(name = "order-service",configuration = LoginFeignConfig.class,
     //fallback =LoginFeignFallback.class
      fallbackFactory = LoginFeignFallbackFactory.class
)
//@FeignClient(name = "order-service")
public interface LoginFeignClient {

    @PostMapping("/login/loginByUsername")
     ApiResponse loginByUsername(@RequestParam String username, @RequestParam String password);


    @PostMapping("/login/loginByBody")
     ApiResponse loginByBody(@RequestBody User user);

    @GetMapping("/login/loginByPath/{username}/{password}")
     ApiResponse loginByPath(@PathVariable String username, @PathVariable String password);

    //默认是json 要加上只对GET有用@SpringQueryMap,对于 post也是把参数拼在url上
     @GetMapping("/login/loginByPojo")
     ApiResponse loginByPojo(@SpringQueryMap User user);
}
