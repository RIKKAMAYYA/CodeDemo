package com.example.concurrentstudy.event;

import com.alibaba.fastjson.JSON;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Project: concurrentstudy
 * @Description:
 * @Author: renxiang
 * @Date: 2022/11/14 17:08
 */
@Component
public class EmailEventListener implements ApplicationListener<OrderEvent> {


    @Override
    public void onApplicationEvent(OrderEvent event) {
        System.out.println(Thread.currentThread().getName() + "-send email: " + JSON.toJSONString(event.getSource()));
    }
}
