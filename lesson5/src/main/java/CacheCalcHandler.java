
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheCalcHandler implements InvocationHandler {

    private final Calculator calculator;
    private final Map<List<Object>, Object> cache;

    public CacheCalcHandler(Calculator calculator) {
        this.calculator = calculator;
        cache = new HashMap<>();
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Cache.class)) {
            if (!cache.containsKey(Arrays.asList(args))) {
                System.out.println("Не из кэша");
                cache.put(Arrays.asList(args), method.invoke(calculator, args));
            } else {
                System.out.println("Из кэша");
            }
            return cache.get(Arrays.asList(args));
        } else {
            return method.invoke(calculator, args);
        }
    }

}
