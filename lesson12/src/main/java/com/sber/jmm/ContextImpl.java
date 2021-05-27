package com.sber.jmm;

public class ContextImpl implements Context {
    private volatile int completed;
    private volatile int failed;
    private volatile int interrupted;
    private volatile boolean isInterrupted = false;
    private volatile boolean isFinished = false;

    @Override
    public int getCompletedTaskCount() {
        return completed;
    }

    @Override
    public int getFailedTaskCount() {
        return failed;
    }

    @Override
    public int getInterruptedTaskCount() {
        return interrupted;
    }

    @Override
    public void interrupt() {
        isInterrupted = true;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    public void finished() {
        isFinished = true;
    }

    public boolean isInterrupted() {
        return isInterrupted;
    }

    public synchronized void addCompletedTaskCount() {
        completed++;
    }

    public synchronized void addFailedTaskCount() {
        failed++;
    }

    public void setInterruptedTaskCount(int count) {
        interrupted = count;
    }
}
