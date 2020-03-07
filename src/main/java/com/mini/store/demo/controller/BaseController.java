package com.mini.store.demo.controller;

import com.mini.store.demo.error.BusinessError;
import com.mini.store.demo.error.BusinessException;
import com.mini.store.demo.response.CommonReturnType;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class BaseController {
    //定义exception handler handle exception not handled by controller
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Object handleException(HttpServletRequest request, Exception ex){
        Map<String, Object> responseData = new HashMap<>();

        if(ex instanceof JwtException) {
            JwtException jwtException = (JwtException) ex;
            responseData.put("code", HttpStatus.UNAUTHORIZED.value());
            responseData.put("msg", jwtException.getMessage());

            return CommonReturnType.builder()
                    .status(HttpStatus.UNAUTHORIZED.value())
                    .data(responseData).build();
        }

        if(ex instanceof BusinessException) {
            BusinessException businessException = (BusinessException) ex;
            responseData.put("code", businessException.getCode());
            responseData.put("msg", businessException.getMsg());
        } else {
            ex.printStackTrace();
            responseData.put("code", BusinessError.UNKNOWN_ERROR.getCode());
            responseData.put("msg", BusinessError.UNKNOWN_ERROR.getMsg());
        }
        return CommonReturnType.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .data(responseData).build();
    }
}
