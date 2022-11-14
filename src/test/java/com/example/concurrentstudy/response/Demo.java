package com.example.concurrentstudy.response;

/**
 * @Project: concurrentstudy
 * @Description:
 * @Author: renxiang
 * @Date: 2022/11/14 14:58
 */
public class Demo {
    public static void main(String[] args) {

        PrivateExceptionEnum.PRIVATE_EXCEPTION_TEMPLATE.assertNotTrue(false,"asd","123");
    }

}
