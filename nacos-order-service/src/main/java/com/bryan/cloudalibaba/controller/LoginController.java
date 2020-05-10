package com.bryan.cloudalibaba.controller;

import com.bryan.cloudalibaba.pojo.ApiResponse;
import com.bryan.cloudalibaba.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @PostMapping("/loginByUsername")
    public ApiResponse loginByUsername(@RequestParam String username, @RequestParam String password){
        log.info("loginByUsername=>"+username+":"+password);
        return ApiResponse.success(username+":"+password);
    }


    @PostMapping("/loginByBody")
    public ApiResponse loginByBody(@RequestBody User user){
        log.info("loginByBody=>"+user.toString());
        return ApiResponse.success(user.toString());
    }

    @GetMapping("/loginByPath/{username}/{password}")
    public ApiResponse loginByPath(@PathVariable String username, @PathVariable String password){
        log.info("loginByPath=>"+username+":"+password);
        return ApiResponse.success(username+":"+password);
    }

    @GetMapping("/loginByPojo")
    public ApiResponse loginByPojo(User user){
        log.info("loginByPojo=>"+user.toString());
        return ApiResponse.success(user.toString());
    }

}
