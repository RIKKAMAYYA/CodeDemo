package com.example.concurrentstudy.response;

import java.text.MessageFormat;

/**
 * @Project: concurrentstudy
 * @Description:
 * @Author: renxiang
 * @Date: 2022/11/14 14:50
 */
public interface BusinessExceptionAssert extends IExceptionEnum, BaseAssert {
    @Override
    default BaseException newException(Object... args){
        String message = this.getMsg();
        // text.MessageFormat 定义插入可选参
        String msg = MessageFormat.format(message, args);
        if (msg.equals(message) && null != args && args.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (Object arg : args) {
                if (null == arg) {
                    continue;
                }
                sb.append(" ").append(arg);
            }
            msg = message + ":" + sb.toString();
        }
        return new BussinessException(this,args,msg);
    }

}
