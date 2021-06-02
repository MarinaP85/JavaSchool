import com.sber.concurrent.proxy.CacheProxyHandler;
import com.sber.concurrent.service.Service;
import com.sber.concurrent.service.ServiceImpl;
import org.junit.Test;

import java.lang.reflect.Proxy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentCacheTest {
    @Test
    public void runTest() {
        CacheProxyHandler.clearCache();
        Service service = (Service) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                ServiceImpl.class.getInterfaces(), new CacheProxyHandler(new ServiceImpl()));
        System.out.println("Проверяем метод run:");

        Runnable task1 = () -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(service.run("test1", 745));
        };
        Runnable task2 = () -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(service.run("test2", 745));
        };
        Runnable task3 = () -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(service.run("test3", 511));
        };
        Runnable task4 = () -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(service.run("test1", 745));
        };

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(task1);
        executor.submit(task2);
        executor.submit(task3);
        executor.submit(task4);

        executor.shutdown();

        try {
            boolean terminated = executor.awaitTermination(20000, TimeUnit.MILLISECONDS);
            System.out.println("terminated: " + terminated);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
