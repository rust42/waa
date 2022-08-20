import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Field;
import java.util.*;

public class MyInjector {

    private Map<String, Object> maps = new HashMap<>();

    MyInjector() throws Exception {
        inject();
    }
    private void inject() throws Exception {
        ConfigurationBuilder beansBuilder = new ConfigurationBuilder();
        beansBuilder.setScanners(Scanners.TypesAnnotated);
        beansBuilder.setUrls(ClasspathHelper.forPackage("."));

        Reflections beansReflections = new Reflections(beansBuilder);
        Set<Class<?>> classes = beansReflections.getTypesAnnotatedWith(MyBean.class);
        for (Class<?> c: classes) {
            Object object = getFromMap(c, c.getDeclaredConstructor().newInstance());
            putIntoMap(c, object);

            Field[] fields = c.getDeclaredFields();

            for (Field field: fields) {
                if (field.getDeclaredAnnotationsByType(MyAutowired.class).length > 0) {
                    Class<?> fieldClass = field.getType();
                    Object fieldObject = getFromMap(fieldClass, fieldClass.getDeclaredConstructor().newInstance());
                    putIntoMap(fieldClass, fieldObject);
                    field.set(object, fieldObject);
                }
            }
        }
    }


    private Object getFromMap(Class<?> klass, Object defaultValue) {
        return maps.getOrDefault(klass.getSimpleName(), defaultValue);
    }

    private void putIntoMap(Class<?> klass, Object object) {
        maps.put(klass.getSimpleName(), object);
    }

    public <T> T getBeans(Class<T> klass) {
        T o = (T)getFromMap(klass, null);
        if (o == null) {
            throw new BeanNotFoundException();
        }
        return o;
    }
}
