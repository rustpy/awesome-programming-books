package com.demo;

import java.util.concurrent.*;

/**
 * @author: yong.peng
 * @create: 2023/10/26 21:15
 **/

public class ThreadPoolDemo {
    private static final ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 5, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>());
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; ++i) {
            poolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    ThreadLocal<BigObject> threadLocal = new ThreadLocal<>();
                    threadLocal.set(new BigObject());
                    // 其他业务代码
                }
            });
            Thread.sleep(1000);
        }
    }
    static class BigObject {
        // 100M
        private byte[] bytes = new byte[100 * 1024 * 1024];
    }

}
