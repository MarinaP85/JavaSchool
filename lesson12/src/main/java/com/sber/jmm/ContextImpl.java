package com.sber.jmm;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ContextImpl implements Context {
    private final AtomicInteger completed = new AtomicInteger(0);
    private final AtomicInteger failed = new AtomicInteger(0);
    private final AtomicInteger interrupted = new AtomicInteger(0);
    private final AtomicBoolean isInterrupted = new AtomicBoolean(false);
    private final AtomicBoolean isFinished = new AtomicBoolean(false);

    @Override
    public int getCompletedTaskCount() {
        return completed.get();
    }

    @Override
    public int getFailedTaskCount() {
        return failed.get();
    }

    @Override
    public int getInterruptedTaskCount() {
        return interrupted.get();
    }

    @Override
    public void interrupt() {
        isInterrupted.set(true);
    }

    @Override
    public boolean isFinished() {
        return isFinished.get();
    }

    public void finished() {
        isFinished.set(true);
    }

    public boolean isInterrupted() {
        return isInterrupted.get();
    }

    public void addCompletedTaskCount() {
        completed.incrementAndGet();
    }

    public void addFailedTaskCount() {
        failed.incrementAndGet();
    }

    public void setInterruptedTaskCount(int count) {
        interrupted.set(count);
    }
}
