import java.lang.reflect.Method;

public class ReflectionApp {
    public static void main(String[] args) {
        String className = args.length > 0 ? args[0] : "TargetClass";

        try {
            Class<?> clazz = Class.forName(className);
            Method method = clazz.getDeclaredMethod("run");
            Object instance = clazz.getDeclaredConstructor().newInstance();
            method.invoke(instance);
        } catch (NoSuchMethodException e) {
            System.err.println("method run() with no arguments not found.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
