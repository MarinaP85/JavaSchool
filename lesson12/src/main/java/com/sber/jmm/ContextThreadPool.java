package com.sber.jmm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ContextThreadPool {
    private final int countThreads;
    private final Queue<Runnable> tasksQueue;
    private final List<Thread> threadsList;
    private final ContextImpl context;

    public ContextThreadPool(int countThreads, ContextImpl context) {
        this.countThreads = countThreads;
        this.context = context;
        tasksQueue = new ConcurrentLinkedQueue<>();
        threadsList = new ArrayList<>();
    }

    public void start() {
        for (int i = 0; i < countThreads; i++) {
            threadsList.add(new Thread(() -> {
                while ((!tasksQueue.isEmpty()) && (!context.isInterrupted())) {
                    try {
                        System.out.println("Thread " + Thread.currentThread().getName());
                        Objects.requireNonNull(tasksQueue.poll()).run();
                        context.addCompletedTaskCount();
                    } catch (Exception e) {
                        context.addFailedTaskCount();
                        Thread.currentThread().interrupt();
                    }
                }
            }));
        }
        for (Thread thread : threadsList) {
            thread.start();
        }
        Thread tmpThread = new Thread(() -> {
            for (Thread thread : threadsList) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        tmpThread.start();
        try {
            tmpThread.join();
            if (context.isInterrupted()) {
                context.setInterruptedTaskCount(tasksQueue.size());
            }
            context.finished();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void execute(Runnable runnable) {
        tasksQueue.add(runnable);
    }

}
