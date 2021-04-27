
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class HomeWork5 {
    public static void main(String[] args) {
        //Задание 1
        Calculator calc = new CalculatorImpl();
        System.out.println("Задание 1:");
        System.out.println("15! = " + calc.calc(15));

        //Задание 2
        System.out.println("Задание 2:");
        printMethods(CalculatorImpl.class);

        //Задание 3
        System.out.println("Задание 3:");
        printGetters(CalculatorImpl.class);
        printGetters(Car.class);

        //Задание 4
        System.out.println("Задание 4:");
        System.out.println(CalculatorImpl.class.toString() + " " + constantName(CalculatorImpl.class));

        //Задание 5
        System.out.println("Задание 5:");
        Calculator calcCache = (Calculator) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                calc.getClass().getInterfaces(), new CacheCalcHandler(calc));
        System.out.println("15! = " + calcCache.calc(15));
        System.out.println("8! = " + calcCache.calc(8));
        System.out.println("3! = " + calcCache.calc(3));
        System.out.println("8! = " + calcCache.calc(8));
        System.out.println("11! = " + calcCache.calc(11));

        //Задание 6
        System.out.println("Задание 6:");
        Calculator calcPerformance = (Calculator) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                calc.getClass().getInterfaces(), new PerformanceProxy(calc));
        System.out.println("15! = " + calcPerformance.calc(15));
        System.out.println("3! = " + calcPerformance.calc(3));
        System.out.println("30! = " + calcPerformance.calc(30));

        //Задание 7
        System.out.println("Задание 7:");
        Car car1 = new Car("Мерседес", "седан");
        Car car2 = new Car("Форд", "хэтчбек");
        Car car3 = new Car();
        System.out.println("car1: " + car1.toString());
        System.out.println("car2: " + car2.toString());
        System.out.println("car3: " + car3.toString());
        BeanUtils.assign(car2, car1);
        BeanUtils.assign(car3, car2);
        System.out.println("После копирования");
        System.out.println("car1: " + car1.toString());
        System.out.println("car2: " + car2.toString());
        System.out.println("car3: " + car3.toString());

    }

    //Задание 2: Вывести на консоль все методы класса, включая все родительские методы (включая приватные)
    public static void printMethods(Class<?> clazz) {
        while (clazz != null) {
            System.out.println(Arrays.toString(clazz.getDeclaredMethods()));
            clazz = clazz.getSuperclass();
        }
    }

    //Задание 3: Вывести все геттеры класса
    public static void printGetters(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().contains("get")) {
                System.out.println(method);
            }
        }
    }

    //Задание 4: Проверить что все String константы имеют значение = их имени
    public static int constantName(Class<?> clazz) {
        int result = -1;
        int modifiers;
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                modifiers = field.getModifiers();
                if ((field.getType() == String.class) && (Modifier.isStatic(modifiers)) && (Modifier.isFinal(modifiers))) {
                    result = 1;
                    field.setAccessible(true);
                    if (!field.getName().equals(field.get(null))) {
                        result = 0;
                        break;
                    }
                }
            }
            return result;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return 3;
        }

    }
}
