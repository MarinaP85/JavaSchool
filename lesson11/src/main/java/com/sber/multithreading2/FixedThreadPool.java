package com.sber.multithreading2;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class FixedThreadPool implements ThreadPool {
    private final int countThreads;
    private final Queue<Runnable> tasksQueue;
    private volatile boolean isRunning = true;

    public FixedThreadPool(int countThreads) {
        this.countThreads = countThreads;
        tasksQueue = new ConcurrentLinkedQueue<>();
    }

    @Override
    public void start() {
        for (int i = 0; i < countThreads; i++) {
            new Thread(() -> {
                while (isRunning) {
                    //pollTask().run();
                    if (!tasksQueue.isEmpty()) {
                        tasksQueue.poll().run();
                        System.out.println("Thread " + Thread.currentThread().getName());
                    }
                }
            }).start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        tasksQueue.offer(runnable);
    }

    public synchronized void stop() {
        isRunning = false;
    }

}
