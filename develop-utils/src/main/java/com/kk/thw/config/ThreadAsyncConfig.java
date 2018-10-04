package com.kk.thw.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ThreadAsyncConfig implements AsyncConfigurer {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());


    @Bean
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        threadPool.setCorePoolSize(10);
        // 设置最大线程数
        threadPool.setMaxPoolSize(100);
        // 线程池所使用的缓冲队列
        threadPool.setQueueCapacity(50);
        // 等待时间（默认为0，此时立即停止），并没等待xx秒后强制停止
        threadPool.setAwaitTerminationSeconds(60);
        threadPool.setKeepAliveSeconds(300);
        // 线程名称前缀
        threadPool.setThreadNamePrefix("Async-");
        // 当线程大于线程池最大值时，设置拒绝策略
        threadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 初始化线程
        threadPool.initialize();
        return threadPool;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new MyAsyncExceptionHandler();
    }

    class MyAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

        // 手动处理捕获的异常
        @Override
        public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
            logger.error("捕获线程异常信息:Exception message - {},Method name - {}", throwable.getMessage(), method.getName());
            for (Object param : obj) {
                logger.error("Parameter value - " + param);
            }
        }

    }
}
