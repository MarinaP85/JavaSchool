
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class BeanUtils {
    /**
     * Scans object "from" for all getters. If object "to"
     * contains correspondent setter, it will invoke it
     * to set property value for "to" which equals to the property
     * of "from".
     * <p/>
     * The type in setter should be compatible to the value returned
     * by getter (if not, no invocation performed).
     * Compatible means that parameter type in setter should
     * be the same or be superclass of the return type of the getter.
     * <p/>
     * The method takes care only about public methods.
     *
     * @param to   Object which properties will be set.
     * @param from Object which properties will be used to get values.
     */
    public static void assign(Object to, Object from) {
        int modifiersFrom;
        int modifiersTo;
        String nameMethodFrom;
        String nameMethodTo;
        Method[] methodsFrom = from.getClass().getDeclaredMethods();
        Method[] methodsTo = from.getClass().getDeclaredMethods();
        for (Method methodFrom : methodsFrom) {
            modifiersFrom = methodFrom.getModifiers();
            nameMethodFrom = methodFrom.getName();
            if ((Modifier.isPublic(modifiersFrom)) && (nameMethodFrom.contains("get"))) {
                nameMethodTo = nameMethodFrom.replaceAll("get", "set");
                for (Method methodTo : methodsTo) {
                    modifiersTo = methodTo.getModifiers();
                    if ((Modifier.isPublic(modifiersTo)) && (methodTo.getName().equals(nameMethodTo)) && (methodFrom.getReturnType() == methodTo.getParameterTypes()[0])) {
                        try {
                            methodTo.invoke(to, methodFrom.invoke(from));
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }
        }
    }

}
