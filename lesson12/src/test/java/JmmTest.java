import com.sber.jmm.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.Callable;

public class JmmTest {

    private volatile Context context;

    @Test
    public void taskTest() {
        Callable<String> callable = () -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "test " + System.currentTimeMillis();
        };

        Task<String> task = new Task<>(callable);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                System.out.println("Thread: " + Thread.currentThread().getName());
                try {
                    System.out.println(task.get());
                } catch (TaskException e) {
                    System.err.println(e.getMessage());
                }
            }).start();
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ExecutionManagerTest() {
        context = new ContextImpl();
        Runnable[] tasks = new Runnable[10];
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            tasks[i] = () -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Task " + finalI);
            };
        }

        Runnable callback = () -> {
            System.out.println("All tasks have finished");
        };

        Thread tmpThread = new Thread(() -> {
            ExecutionManager executionManager = new ExecutionManagerImpl();
            context = executionManager.execute(callback, tasks);
        });
        tmpThread.start();

        try {
            tmpThread.join();
            Assert.assertEquals(context.getCompletedTaskCount(), 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
