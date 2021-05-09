import com.sber.proxy.CacheProxyHandler;
import com.sber.service.Service;
import com.sber.service.ServiceImpl;
import org.junit.Test;

import java.lang.reflect.Proxy;

public class CacheProxyTest {
    @Test
    public void runTest() {
        Service service = (Service) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                ServiceImpl.class.getInterfaces(), new CacheProxyHandler(new ServiceImpl()));
        System.out.println("Проверяем метод run:");
        System.out.println(service.run("test1", 745));
        System.out.println(service.run("test2", 745));
        System.out.println(service.run("test3", 511));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(service.run("test1", 745));
        System.out.println(service.run("test3", 511));
    }

    @Test
    public void workTest() {
        Service service = (Service) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                ServiceImpl.class.getInterfaces(), new CacheProxyHandler(new ServiceImpl()));
        System.out.println("Проверяем метод work:");
        System.out.println(service.work("test1"));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(service.work("test2"));
        System.out.println(service.work("test3"));
        System.out.println(service.work("test1"));
    }
}
