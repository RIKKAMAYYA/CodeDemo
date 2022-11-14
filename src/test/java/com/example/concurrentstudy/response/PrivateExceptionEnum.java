package com.example.concurrentstudy.response;

/**
 * @PROJECT_NAME: concurrentstudy
 * @DESCRIPTION:
 * @USER: renxiang
 * @DATE: 2022/11/14 14:55
 */
public enum PrivateExceptionEnum implements IExceptionEnum, BusinessExceptionAssert {
    /**
     * 异常模板
     */
    PRIVATE_EXCEPTION_TEMPLATE(100, "exception msg {0}{1}");

    PrivateExceptionEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    int code;
    String msg;


    @Override
    public int code() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
