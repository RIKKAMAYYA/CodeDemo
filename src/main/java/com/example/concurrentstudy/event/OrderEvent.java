package com.example.concurrentstudy.event;

/**
 * @Project: concurrentstudy
 * @Description:
 * @Author: renxiang
 * @Date: 2022/11/14 17:01
 */
public class OrderEvent extends AbstractGenericEvent<OrderModel> {

    public OrderEvent(OrderModel source) {
        super(source);
    }

}
