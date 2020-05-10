package com.bryan.cloudalibaba.sentinel;

import com.alibaba.cloud.sentinel.rest.SentinelClientHttpResponse;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import com.bryan.cloudalibaba.pojo.ApiResponse;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;

public class ExceptionUtils {


    public static  ApiResponse blockMethod(String username, String password, BlockException exception){
        exception.printStackTrace();
        return ApiResponse.failure(-1,"block:"+username+"=="+password);
    }



    public static  ApiResponse exceptionMethod( String username,  String password,Throwable throwable){
        return ApiResponse.failure(-1,"exception:"+username+"=="+password);
    }


    public static ClientHttpResponse restBlockMethod(HttpRequest request, byte[] body, ClientHttpRequestExecution execution, BlockException exception){
        exception.printStackTrace();
        String error= JSON.toJSONString(ApiResponse.failure(-1,"restBlockMethod"));
        return new SentinelClientHttpResponse(error);
    }




    public static  ClientHttpResponse restExceptionMethod( HttpRequest request, byte[] body, ClientHttpRequestExecution execution, BlockException exception){
        exception.printStackTrace();
        String error= JSON.toJSONString(ApiResponse.failure(-1,"restExceptionMethod"));
        return new SentinelClientHttpResponse(error);
    }
}
