package com.example.concurrentstudy.response;

/**
 * @Project: concurrentstudy
 * @Description:    基础断言
 * @Author: renxiang
 * @Date: 2022/11/14 14:38
 */
public interface  BaseAssert {

    BaseException newException(Object... args);

    default void assertNotNull(Object o) {
        if (o == null) {
            throw newException(o);
        }
    }

    default void assertNotTrue(boolean flag,Object... args) {
        if (!flag) {
            throw newException(args);
        }
    }
}
