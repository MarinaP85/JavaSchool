import com.sber.multithreading2.FixedThreadPool;
import com.sber.multithreading2.ScalableThreadPool;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ThreadPoolTest {

    @Test
    public void fixedThreadPoolTest() {
        List<Runnable> tasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            tasks.add(() -> System.out.println("Task " + finalI));
        }

        FixedThreadPool fixedThreadPool = new FixedThreadPool(5);
        fixedThreadPool.execute(tasks.get(0));
        fixedThreadPool.execute(tasks.get(1));
        fixedThreadPool.start();
        for (int i = 2; i < 10; i++) {
            fixedThreadPool.execute(tasks.get(i));
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        fixedThreadPool.stop();
    }

    @Test
    public void scalableThreadPoolTest() {
        List<Runnable> tasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            tasks.add(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Task " + finalI);
            });
        }

        ScalableThreadPool scalableThreadPool = new ScalableThreadPool(2, 5);
        scalableThreadPool.execute(tasks.get(0));
        scalableThreadPool.execute(tasks.get(1));
        scalableThreadPool.execute(tasks.get(2));
        scalableThreadPool.start();
        for (int i = 3; i < 10; i++) {
            scalableThreadPool.execute(tasks.get(i));
        }

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        scalableThreadPool.stop();
    }
}
