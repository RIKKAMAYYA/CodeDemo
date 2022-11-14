package com.example.concurrentstudy.asyncpractice;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.*;

import static java.lang.Thread.sleep;


/**
 * @PROJECT_NAME: concurrentstudy
 * @DESCRIPTION: 异步的7种写法
 * @USER: renxiang
 * @DATE: 2022/11/14 12:16
 */
@SpringBootTest
public class AsyncTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncTest.class);

    public static ExecutorService es = new ThreadPoolExecutor(4, 40, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1024), new ThreadFactoryBuilder().setNameFormat("defaultExecutorService-%d").build(), (r, executor) -> LOGGER.error("defaultExecutor pool is full! "));

    /**
     * 方式一 到 方式三都是新建了一个新子线程
     */
    class AsyncThread extends Thread {
        @Override
        public void run() {
            System.out.println("执行子线程异步逻辑");
        }
    }

    class AsyncThread1 implements Runnable {
        @Override
        public void run() {
            System.out.println("执行子线程异步逻辑2");
        }
    }

    class AsyncThread2 implements Callable<Integer> {
        private Integer a;

        public AsyncThread2(Integer a) {
            this.a = a;
        }

        @Override
        public Integer call() throws Exception {
            System.out.println("执行子线程异步逻辑");
            for (int i = 0; i < 1000; i++) {
                a++;
            }

            return a;
        }
    }

    @Test
    void test1() {
        System.out.println("主线程逻辑");
        new AsyncThread().start();
        new Thread(new AsyncThread1()).start();
    }

    @Test
    void test2() {
        System.out.println("主线程逻辑");
        Future<Integer> future = es.submit(new AsyncThread2(1));
        try {
            System.out.println(future.get());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            es.shutdown();
        }
    }

    /**
     * futureTask
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    void test3() throws ExecutionException, InterruptedException {
        System.out.println("主线程逻辑");
        FutureTask<Integer> futureTask =   new FutureTask<Integer>(() ->{
            System.out.println("子线程开始计算");
            int sum = 0;
            for (int i = 0; i < 100; i++) {
                sum++;
            }
            return sum;
        });
        es.submit(futureTask);
        System.out.println(futureTask.get());
        es.shutdown();
    }

    /**
     * CompletableFuture
     */
    @Test
    void test4() {
        CompletableFuture<Void> f1 =
                CompletableFuture.runAsync(() -> {
                    System.out.println("洗水壶");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });

    }
}
