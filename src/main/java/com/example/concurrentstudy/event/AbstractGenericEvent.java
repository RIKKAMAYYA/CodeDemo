package com.example.concurrentstudy.event;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * @Project: concurrentstudy
 * @Description:    自定义业务事件子类，继承 ApplicationEvent，通过泛型注入业务模型参数类
 * @Author: renxiang
 * @Date: 2022/11/14 17:02
 */
public abstract class AbstractGenericEvent<T> extends ApplicationEvent {


    public AbstractGenericEvent(Object source) {
        super(source);
    }

    public AbstractGenericEvent(Object source, Clock clock) {
        super(source, clock);
    }
}
