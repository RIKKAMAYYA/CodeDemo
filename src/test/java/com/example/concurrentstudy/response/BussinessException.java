package com.example.concurrentstudy.response;

/**
 * @Project: concurrentstudy
 * @Description:    业务异常
 * @Author: renxiang
 * @Date: 2022/11/14 14:36
 */
public class BussinessException extends BaseException{
    private static final long serialVersionUID = 1L;

    public BussinessException(IExceptionEnum iExceptionEnum, Object[] args ,String message ) {
        super(message, iExceptionEnum, args);
    }
}
