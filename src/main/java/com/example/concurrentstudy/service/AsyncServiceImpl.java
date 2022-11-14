package com.example.concurrentstudy.service;

import com.alibaba.fastjson.JSONObject;
import com.example.concurrentstudy.entity.UserDTO;
import com.example.concurrentstudy.event.OrderEvent;
import com.example.concurrentstudy.event.OrderModel;
import com.example.concurrentstudy.utils.LambdaUtil;
import com.example.concurrentstudy.utils.SpringUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @Project: concurrentstudy
 * @Description:
 * @Author: renxiang
 * @Date: 2022/11/14 15:51
 */
@Service
public class AsyncServiceImpl implements AsyncService {
    @Override
    @Async("defaultThreadPoolExecutor")
    public Future<Integer> testAsync(int num, int sum) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //AtomicInteger a = new AtomicInteger(0);

        for (int i = 0; i < num; i++) {
            sum++;
            //a.incrementAndGet();
            System.out.println("线程：" + Thread.currentThread().getName() + " , 任务：" + sum);
        }


        //return new AsyncResult<Integer>(a.get());
        return new AsyncResult<Integer>(sum);
    }

    @Override
    public void testEvent() {
        OrderModel orderModel = new OrderModel();
        orderModel.setId(1000L);
        orderModel.setName("test");
        SpringUtil.getContext().publishEvent(new OrderEvent(orderModel));
        System.out.println(Thread.currentThread().getName() + "-publish: ");
    }

    @Override
    public void test(UserDTO request) throws InterruptedException {
        Long id = request.getId();
        CompletableFuture<Void> future = CompletableFuture.runAsync(LambdaUtil.wrapRunnable2(() -> {
            TimeUnit.MILLISECONDS.sleep(200);
            System.out.println("运行在一个单独的进程中");
            long i = 1 / id;
            System.out.println(i);
        }));
    }

    @Override
    public void testJson(JSONObject request) throws InterruptedException {
        Long id = request.getLong("id");
        CompletableFuture<Void> future = CompletableFuture.runAsync(LambdaUtil.wrapRunnable2(() -> {
            TimeUnit.MILLISECONDS.sleep(200);
            System.out.println("运行在一个单独的进程中");

            long i = 1 / id;
            System.out.println(i);
        }));
    }
}
