package com.sber.jmm;

import java.util.concurrent.Callable;

public class Task<T> {
    private volatile Callable<? extends T> callable = null;
    private volatile T taskResult;
    private volatile boolean exception;

    public Task(Callable<? extends T> callable) {
        this.callable = callable;
        this.taskResult = null;
        this.exception = false;
    }

    public T get() throws TaskException {
        if (exception) {
            throw new TaskException("Callable Exception!");
        }
        if ((taskResult == null) && (!exception)) {
            synchronized (this) {
                if ((taskResult == null) && (!exception)) {
                    try {
                        taskResult = callable.call();
                    } catch (Exception e) {
                        exception = true;
                        throw new TaskException("Callable Exception!");
                    }
                }
            }
        }
        return taskResult;
    }

}
