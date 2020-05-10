package com.bryan.cloudalibaba.controller;

import com.bryan.cloudalibaba.pojo.ApiResponse;
import com.bryan.cloudalibaba.pojo.User;
import com.bryan.cloudalibaba.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@RefreshScope
@Slf4j
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Value("${user.address}")
    private String address;

    /**
     * 对sentinel来说所有的RequestMapping都是资源
     * @SentinelResource  可以标记一个资源
     * @param username
     * @param password
     * @return
     *
     * 要配置SentinelResource标记的这个资源才会响应blockHandler
     * 只配置fallback可以接手blockHandler
     */
//    @SentinelResource(value = "sloginByUsername",blockHandler = "blockMethod",blockHandlerClass = ExceptionUtils.class,fallback = "exceptionMethod",fallbackClass =ExceptionUtils.class )
    @PostMapping("/loginByUsername")
    public ApiResponse loginByUsername(@RequestParam String username, @RequestParam String password){
        log.info(address);
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        if(username.equals("xx")){
            int i=1/0;
        }

        return loginService.loginByUsername(username,password);
    }

    @PostMapping("/loginByBody")
    public ApiResponse loginByBody(@RequestBody User user){
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return loginService.loginByBody(user);
    }

    @GetMapping("/loginByPath/{username}/{password}")
    public ApiResponse loginByPath(@PathVariable String username, @PathVariable String password){
        return loginService.loginByPath(username,password);
    }

    @GetMapping("/loginByPojo")
    public ApiResponse loginByPojo(User user){
        log.info(user.toString());
        return loginService.loginByPojo(user);
    }
}
