package com.example.springboot.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理器
 * @author luowei
 * @date 2019/5/8 19:42
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Map<String,Object> handleException(Exception e){
        Map<String,Object> map=new HashMap<>();
        map.put("errorCode","500");
        map.put("errorMsg",e.toString());
        return map;
    }
}
