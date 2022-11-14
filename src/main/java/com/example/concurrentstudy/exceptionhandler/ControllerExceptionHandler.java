package com.example.concurrentstudy.exceptionhandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Project: concurrentstudy
 * @Description:    统一异常处理类
 * @Author: renxiang
 * @Date: 2022/11/15 0:32
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        String msg = "get an exception in handleException method";
        System.out.println("msg: " + msg);
        return msg;
    }


}
