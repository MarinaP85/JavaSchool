
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PerformanceProxy implements InvocationHandler {

    private final Calculator calculator;

    public PerformanceProxy(Calculator calculator) {
        this.calculator = calculator;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTime = System.nanoTime();
        Object invoke = method.invoke(calculator, args);
        System.out.println("Расчет произведен за " + (System.nanoTime() - startTime) + " наносек");
        return invoke;
    }
}
