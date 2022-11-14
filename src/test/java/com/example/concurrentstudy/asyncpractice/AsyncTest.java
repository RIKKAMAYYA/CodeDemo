package com.example.concurrentstudy.asyncpractice;

import com.example.concurrentstudy.event.OrderEvent;
import com.example.concurrentstudy.event.OrderModel;
import com.example.concurrentstudy.service.AsyncService;
import com.example.concurrentstudy.utils.SpringUtil;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;


/**
 * @PROJECT_NAME: concurrentstudy
 * @DESCRIPTION: 异步的7种写法
 * @USER: renxiang
 * @DATE: 2022/11/14 12:16
 */
@SpringBootTest
@Service
public class AsyncTest {
    @Autowired
    private AsyncService asyncService;

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
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    void test3() throws ExecutionException, InterruptedException {
        System.out.println("主线程逻辑");
        FutureTask<Integer> futureTask = new FutureTask<Integer>(() -> {
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
        //任务1 洗水壶->烧水
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {
            System.out.println("t1 洗水壶");
            sleep(1, TimeUnit.SECONDS);

            System.out.println("t1 烧水");
            sleep(3, TimeUnit.SECONDS);
        });

        //任务2 洗茶壶->洗茶杯->拿茶叶
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("t2 洗茶壶");
            sleep(1, TimeUnit.SECONDS);

            System.out.println("t2 洗茶杯");
            sleep(3, TimeUnit.SECONDS);

            System.out.println("t2 拿茶叶");
            sleep(3, TimeUnit.SECONDS);

            return "龙井";
        });

        //任务3：任务1和任务2完成后执行：泡茶
        CompletableFuture<String> f3 = f1.thenCombine(f2, (__, tf) -> {
            System.out.println("T1:拿到茶叶:" + tf);
            System.out.println("T1:泡茶...");
            return "上茶:" + tf;
        });

       /* //任务3：任务1和任务2完成后执行：泡茶
        CompletableFuture<String> f4 =
                f1.thenCombine(f2, new BiFunction<Void, String, String>() {
                    @Override
                    public String apply(Void unused, String s) {
                        System.out.println("T1:拿到茶叶:" + s);
                        System.out.println("T1:泡茶...");
                        return "上茶:" + s;
                    }
                });
*/

        //等待任务3执行结果
        System.out.println(f3.join());
    }

    private void sleep(int i, TimeUnit unit) {
        try {
            unit.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @EnableAsync 调用异步方法类上或者启动类加上注解 @EnableAsync
     * 在需要被异步调用的方法外加上 @Async
     * 所使用的 @Async 注解方法的类对象应该是 Spring 容器管理的 bean 对象；
     */
    @Test
    void test5() throws ExecutionException, InterruptedException {
        Integer s = 0;
        for (int i = 0; i < 20; i++) {
             asyncService.testAsync(1000, 0);
        }
        System.out.println(s);
        System.out.println("主线程执行");
    }

    @Test
    void test6() {
        OrderModel orderModel = new OrderModel();
        orderModel.setId(1000L);
        orderModel.setName("test");
        SpringUtil.getContext().publishEvent(new OrderEvent(orderModel));
        System.out.println(Thread.currentThread().getName() + "-publish: ");
    }


}
