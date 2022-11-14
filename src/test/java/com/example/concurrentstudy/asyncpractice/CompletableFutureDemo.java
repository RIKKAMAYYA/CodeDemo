package com.example.concurrentstudy.asyncpractice;

import com.example.concurrentstudy.utils.LambdaUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Project: concurrentstudy
 * @Description:
 * @Author: renxiang
 * @Date: 2022/11/14 23:50
 */
@SpringBootTest
public class CompletableFutureDemo {
    @Test
    void test1() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        //手动结束
        completableFuture.complete("stop manually");
        String res = completableFuture.get();

        System.out.println(res);
        System.out.println("res");
    }

    @Test
    void test2() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(LambdaUtil.wrapRunnable2(() -> {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("运行在一个单独的进程中");
        }));
    }

}
