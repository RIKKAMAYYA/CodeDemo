package com.example.concurrentstudy;

import com.example.concurrentstudy.utils.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ConcurrentstudyApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ConcurrentstudyApplication.class, args);
        SpringUtil.set(context);

    }

}
