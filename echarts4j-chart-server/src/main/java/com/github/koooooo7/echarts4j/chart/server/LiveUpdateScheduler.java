package com.github.koooooo7.echarts4j.chart.server;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public final class LiveUpdateScheduler {
    private LiveUpdateScheduler() {
    }


    private static final ScheduledExecutorService poolExecutor = new ScheduledThreadPoolExecutor(6, new ThreadFactory() {
        private final AtomicInteger cnt = new AtomicInteger();

        @Override
        public Thread newThread(Runnable r) {
            final Thread t = new Thread(r, "LiveUpdateSchedulerWorker-" + cnt.getAndIncrement());
            t.setDaemon(true);
            return t;
        }
    });


    public static void register(Runnable runnable, long initialDelay, long period, TimeUnit unit) {
        poolExecutor.scheduleAtFixedRate(runnable, initialDelay, period, unit);
    }


}
