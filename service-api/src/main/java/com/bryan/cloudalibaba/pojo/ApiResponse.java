package com.bryan.cloudalibaba.pojo;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

    public static final int SUCCESS=0;

    private int code;
    private String message;
    private T data;


    public static ApiResponse success(String message){
        return ApiResponse.builder().code(SUCCESS).message(message).build();
    }

    public static <T>  ApiResponse<T> success(String message,T data){
        return new ApiResponseBuilder<T>().code(SUCCESS).message(message).data(data).build();
    }


    public static ApiResponse failure(int code,String message){
        return ApiResponse.builder().code(code).message(message).build();
    }

    public static <T>  ApiResponse<T> failure(int code,String message,T data){
        return new ApiResponseBuilder<T>().code(code).message(message).data(data).build();
    }
}
