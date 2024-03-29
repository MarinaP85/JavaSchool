package com.sber.jmm;

public class ExecutionManagerImpl implements ExecutionManager {
    private volatile ContextImpl context;


    @Override
    public Context execute(Runnable callback, Runnable... tasks) {
        context = new ContextImpl();

        Thread tmpThread = new Thread(() -> {
            ContextThreadPool threadPool = new ContextThreadPool(5, context);
            for (Runnable task : tasks) {
                threadPool.execute(task);
            }
            threadPool.start();
        });
        tmpThread.start();

        new Thread(() -> {
            try {
                tmpThread.join();
                callback.run();
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }).start();

        return context;
    }

}
