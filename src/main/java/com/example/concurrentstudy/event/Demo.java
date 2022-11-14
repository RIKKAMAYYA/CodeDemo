package com.example.concurrentstudy.event;

import com.example.concurrentstudy.utils.SpringUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Project: concurrentstudy
 * @Description:
 * @Author: renxiang
 * @Date: 2022/11/14 17:20
 */
@SpringBootTest
public class Demo {

    @Test
    void test01() {
        OrderModel orderModel = new OrderModel();
        orderModel.setId(1000L);
        orderModel.setName("test");
        SpringUtil.getContext().publishEvent(new OrderEvent(orderModel));
    }

}
