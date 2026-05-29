import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HomeworkAnalyzer {

    public static void main(String[] args) {
        String folderPath = args.length > 0 ? args[0] : ".";
        File rootFolder = new File(folderPath);

        if (!rootFolder.exists() || !rootFolder.isDirectory()) {
            System.err.println("error");
            return;
        }

        try {
            URL url = rootFolder.toURI().toURL();
            URLClassLoader classLoader = new URLClassLoader(new URL[]{url}, HomeworkAnalyzer.class.getClassLoader());

            List<Class<? extends Annotation>> annotationTypes = new ArrayList<>();
            List<Class<?>> publicClasses = new ArrayList<>();

            try (Stream<Path> paths = Files.walk(Paths.get(folderPath))) {
                List<Path> classFiles = paths
                        .filter(Files::isRegularFile)
                        .filter(p -> p.toString().endsWith(".class"))
                        .collect(Collectors.toList());

                for (Path path : classFiles) {
                    String relativePath = rootFolder.toPath().relativize(path).toString();
                    String className = relativePath.replace(File.separatorChar, '.').replace(".class", "");

                    try {
                        Class<?> clazz = classLoader.loadClass(className);

                        if (clazz.isAnnotation()) {
                            annotationTypes.add(clazz.asSubclass(Annotation.class));
                        } else if (Modifier.isPublic(clazz.getModifiers()) && !clazz.isInterface()) {
                            publicClasses.add(clazz);
                        }
                    } catch (ClassNotFoundException | NoClassDefFoundError e) {

                    }
                }
            }


            annotationTypes.forEach(a -> System.out.println(a.getName()));


            for (Class<?> pubClass : publicClasses) {
                printClassPrototype(pubClass);
                invokeAnnotatedMethods(pubClass, annotationTypes);
            }

            classLoader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printClassPrototype(Class<?> clazz) {
        System.out.println(clazz.getName());

        for (Field field : clazz.getDeclaredFields()) {
            System.out.println(field.getType().getSimpleName() + " " + field.getName());
        }

        for (Constructor<?> ctor : clazz.getDeclaredConstructors()) {
            System.out.println(ctor.toString());
        }

        for (Method method : clazz.getDeclaredMethods()) {
            System.out.println(method.getReturnType().getSimpleName() + " " + method.getName());
        }
    }

    private static void invokeAnnotatedMethods(Class<?> clazz, List<Class<? extends Annotation>> annotations) {
        Object instance = null;
        boolean instanceCreated = false;

        for (Method method : clazz.getDeclaredMethods()) {
            boolean isAnnotated = annotations.stream().anyMatch(method::isAnnotationPresent);

            if (isAnnotated) {
                try {
                    if (!Modifier.isStatic(method.getModifiers()) && !instanceCreated) {
                        instance = clazz.getDeclaredConstructor().newInstance();
                        instanceCreated = true;
                    }

                    int paramCount = method.getParameterCount();
                    Class<?>[] paramTypes = method.getParameterTypes();

                    if (paramCount == 0) {
                        System.out.println(method.invoke(instance));
                    } else if (paramCount == 1 && (paramTypes[0] == int.class || paramTypes[0] == Integer.class)) {
                        System.out.println(method.invoke(instance, 42));
                    }

                } catch (Exception e) {

                }
            }
        }
    }
}
