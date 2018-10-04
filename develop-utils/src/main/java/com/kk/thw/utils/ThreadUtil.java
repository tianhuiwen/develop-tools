package com.kk.thw.utils;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Future;

/**
 * 线程处理工具类
 *
 * @author tianhuiwen
 * @date 2018-09-28
 */
@Component
public class ThreadUtil {
    /**
     * 等待所有线程把全部数据处理完毕
     *
     * @param futures
     */
    public void waitingtThreadDealData(List<Future> futures) {
        while (true) {
            for (int i = 0; i < futures.size(); i++) {
                Future future = futures.get(i);
                if (future.isDone() && !future.isCancelled()) {
                    futures.remove(future);
                } else {
                    try {
                        // 避免CPU高速运转，这里休息1毫秒，CPU纳秒级别
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException();
                    }
                }
            }
            if (futures.size() == 0) {
                break;
            }
        }
    }
}
