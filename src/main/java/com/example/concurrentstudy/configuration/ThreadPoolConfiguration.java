package com.example.concurrentstudy.configuration;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.SimpleApplicationEventMulticaster;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Project: concurrentstudy
 * @Description:
 * @Author: renxiang
 * @Date: 2022/11/14 15:35
 */
@Configuration
@Slf4j
public class ThreadPoolConfiguration {

    @Bean(name = "defaultThreadPoolExecutor", destroyMethod = "shutdown")
    public ThreadPoolExecutor systemCheckPoolExecutorService() {
        return new ThreadPoolExecutor(3, 10, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10000), new ThreadFactoryBuilder().setNameFormat("default-executor-%d").build(), (r, executor) -> {
            log.error("system pool is full");
        });
    }


    @Bean
    public SimpleApplicationEventMulticaster applicationEventMulticaster(@Qualifier("defaultThreadPoolExecutor") ThreadPoolExecutor executor) {
        SimpleApplicationEventMulticaster simpleApplicationEventMulticaster = new SimpleApplicationEventMulticaster();
        simpleApplicationEventMulticaster.setTaskExecutor(executor);
        return simpleApplicationEventMulticaster;
    }
}
