package com.sber.multithreading2;

import java.util.*;

public class ScalableThreadPool implements ThreadPool {

    private final int min;
    private final int max;

    private final Queue<Runnable> tasksQueue;
    private volatile boolean isRunning = true;
    private volatile int countTasks = 3;
    private final List<Boolean> threadStateList;
    private final Object lock = new Object();

    public ScalableThreadPool(int min, int max) {
        this.min = min;
        this.max = max;
        tasksQueue = new LinkedList<>();
        threadStateList = Collections.synchronizedList(new ArrayList<>());
    }

    @Override
    public void start() {
        for (int i = 0; i < min; i++) {
            int finalI = i;
            threadStateList.add(i, false);
            Thread tmpThread = new Thread(() -> {
                while (isRunning) {
                    pollTask(finalI).run();
                }
            });
            tmpThread.start();
        }
        Thread tmpThreadAfterMin = new Thread(() -> {
            while (isRunning) {
                if (countTasks <= max) {
                    pollTaskAfterMin();
                }
            }
        });
        tmpThreadAfterMin.start();
    }

    @Override
    public synchronized void execute(Runnable runnable) {
        tasksQueue.add(runnable);
        notifyAll();
    }

    public void stop() {
        isRunning = false;
    }

    private synchronized Runnable pollTask(int threadNumber) {
        while (tasksQueue.isEmpty()) {
            threadStateList.set(threadNumber, false);
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        threadStateList.set(threadNumber, true);
        System.out.println("Работает основной блок");
        notifyAll();
        return tasksQueue.poll();
    }

    private synchronized void pollTaskAfterMin() {
        while (threadStateList.contains(false)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if ((!tasksQueue.isEmpty()) && (!threadStateList.contains(false))) {
            countTasks++;
            Thread tmpThread = new Thread(() -> {
                Runnable task = tasksQueue.poll();
                if (task != null) {
                    task.run();
                    synchronized (lock) {
                        countTasks--;
                    }
                }
            });
            tmpThread.start();
            System.out.println("Заданий больше минимума, зашли в дополнительный блок");
        }
    }
}
