package com.sber.multithreading2;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ScalableThreadPool implements ThreadPool {

    private final int min;
    private final int max;

    private final Queue<Runnable> tasksQueue;
    private volatile boolean isRunning = true;
    private volatile int countTasks;
    private final List<Boolean> threadStateList;
    private final Object lock = new Object();

    public ScalableThreadPool(int min, int max) {
        this.min = min;
        this.max = max;
        tasksQueue = new ConcurrentLinkedQueue<>();
        threadStateList = Collections.synchronizedList(new ArrayList<>());
        countTasks = min + 1;
    }

    @Override
    public void start() {
        for (int i = 0; i < min; i++) {
            int finalI = i;
            threadStateList.add(i, false);
            Thread tmpThread = new Thread(() -> {
                while (isRunning) {
                    if (!tasksQueue.isEmpty()) {
                        threadStateList.set(finalI, true);
                        System.out.println("Работает основной блок");
                        System.out.println("Thread " + Thread.currentThread().getName());
                        Objects.requireNonNull(tasksQueue.poll()).run();
                        threadStateList.set(finalI, false);
                    }
                }
            });
            tmpThread.start();
        }
        Thread tmpThreadAfterMin = new Thread(() -> {
            while (isRunning) {
                if (countTasks <= max) {
                    //pollTaskAfterMin();
                    if ((!tasksQueue.isEmpty()) && (!threadStateList.contains(false))) {
                        synchronized (lock) {
                            countTasks++;
                        }
                        Thread tmpThread = new Thread(() -> {
                            System.out.println("Заданий больше минимума, зашли в дополнительный блок");
                            System.out.println("Thread " + Thread.currentThread().getName());
                            Objects.requireNonNull(tasksQueue.poll()).run();
                            synchronized (lock) {
                                countTasks--;
                            }
                        });
                        tmpThread.start();
                    }
                }
            }
        });
        tmpThreadAfterMin.start();
    }

    @Override
    public void execute(Runnable runnable) {
        tasksQueue.offer(runnable);
    }

    public synchronized void stop() {
        isRunning = false;
    }
}
