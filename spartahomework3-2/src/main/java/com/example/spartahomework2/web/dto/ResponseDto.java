package com.example.spartahomework2.web.dto;

import com.example.spartahomework2.exception.response.ErrorRes;
import lombok.Getter;

@Getter
public class ResponseDto<T> {

    private final Boolean success;

    private final T data;

    private final ErrorRes<?> error;

    private ResponseDto(boolean success, T data, ErrorRes<?> errorRes){
        this.success = success;
        this.data = data;
        this.error = errorRes;
    }

    // T - 데이터 타입
    public static <T> ResponseDto<T> ofSuccess(T data){

        return new ResponseDto<>(true, data, null);

    }

    //T - 에러 타입(fieldError)
    public static <T> ResponseDto<T> ofFail(ErrorRes<T> errorRes){

        return new ResponseDto<>(false, null, errorRes);

    }
}
