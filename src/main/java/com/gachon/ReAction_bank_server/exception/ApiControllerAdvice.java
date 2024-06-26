package com.gachon.ReAction_bank_server.exception;

import com.gachon.ReAction_bank_server.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiControllerAdvice {

    /**
     * 회원가입 시 중복된 ID, 계좌번호 입력 시 호출
     * 로그인 시 ID, 비밀번호가 잘못될 경우 호출
     * @param e
     * @return
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse IllegalArgumentExHandle(IllegalArgumentException e){
        return ApiResponse.fail(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    /**
     * @NotEmpty, @Pattern 등 Spring validation에 걸릴 경우 호출
     * @param e
     * @return
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse BindExHandle(BindException e){
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        return ApiResponse.fail(HttpStatus.BAD_REQUEST, objectError.getDefaultMessage());
    }
}
