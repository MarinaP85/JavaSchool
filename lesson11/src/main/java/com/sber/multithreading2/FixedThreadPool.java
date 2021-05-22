package com.sber.multithreading2;

import java.util.LinkedList;
import java.util.Queue;

public class FixedThreadPool implements ThreadPool {
    private final int countThreads;
    private final Queue<Runnable> tasksQueue;
    private volatile boolean isRunning = true;

    public FixedThreadPool(int countThreads) {
        this.countThreads = countThreads;
        tasksQueue = new LinkedList<>();
    }

    @Override
    public void start() {
        for (int i = 0; i < countThreads; i++) {
            new Thread(() -> {
                while (isRunning) {
                    pollTask().run();
                }
            }).start();
        }
    }

    @Override
    public synchronized void execute(Runnable runnable) {
        tasksQueue.add(runnable);
        notifyAll();
    }

    public void stop() {
        isRunning = false;
    }

    private synchronized Runnable pollTask() {
        while (tasksQueue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return tasksQueue.poll();
    }
}
