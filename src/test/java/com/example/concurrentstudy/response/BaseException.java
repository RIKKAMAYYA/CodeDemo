package com.example.concurrentstudy.response;

/**
 * @PROJECT_NAME: concurrentstudy
 * @DESCRIPTION: leetcode
 * @Author: renxiang
 * @DATE: 2022/11/14 14:15
 */
public class BaseException extends RuntimeException{
    protected IExceptionEnum iExceptionEnum;

    protected Object[] args;

    public BaseException(IExceptionEnum responseEnum) {
        super(responseEnum.getMsg());
        this.iExceptionEnum = responseEnum;
    }

    public BaseException(String message, IExceptionEnum iExceptionEnum, Object[] args) {
        super(message);
        this.iExceptionEnum = iExceptionEnum;
        this.args = args;
    }

    public BaseException(String message, Throwable cause, IExceptionEnum iExceptionEnum, Object[] args) {
        super(message, cause);
        this.iExceptionEnum = iExceptionEnum;
        this.args = args;
    }

    public IExceptionEnum getiExceptionEnum() {
        return iExceptionEnum;
    }

    public Object[] getArgs() {
        return args;
    }
}
