package com.bryan.cloudalibaba.controller;

import com.bryan.cloudalibaba.feignclient.BaiduFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private BaiduFeignClient baiduFeignClient;

    @GetMapping("/{id}/{page}")
    public String visit(@PathVariable  Integer id, @PathVariable Integer page){
        return id+":"+page;
    }


    @GetMapping
    public String index(){
        return baiduFeignClient.index();
    }

}
