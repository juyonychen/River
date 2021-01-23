
package com.river.common.utils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A reflection for creating a instance by given class and parameters, inspired by JUnit 5
 *
 *
 * @version 1.0.0, 2018-06-01 04:46
 * @since 1.0.0, 2018-06-01 04:46
 */
@UtilityClass
public class ReflectionSupport {

    @SneakyThrows
    public static <T> T newInstance(Class<T> clazz, Class<?>[] parameterTypes, Object... args) {
        checkNotNull(clazz, "Class must not be null");
        checkNotNull(parameterTypes, "Type array must not be null");
        checkNotNull(args, "Argument array must not be null");

        return newInstance(clazz.getDeclaredConstructor(parameterTypes), args);
    }

    @SneakyThrows
    public static <T> T newInstance(Class<T> clazz, Object... args) {
        checkNotNull(clazz, "Class must not be null");
        checkNotNull(args, "Argument array must not be null");

        Class<?>[] parameterTypes = Arrays.stream(args)
            .filter(Objects::nonNull)
            .map(Object::getClass)
            .toArray(Class[]::new);

        return newInstance(clazz.getDeclaredConstructor(parameterTypes), args);
    }

    /**
     * Create a new instance of type {@code T} by invoking the supplied constructor
     * with the supplied arguments.
     *
     * <p>The constructor will be made accessible if necessary, and any checked
     * exception will be convert to an unchecked exception.
     *
     * @param constructor the constructor to invoke; never {@code null}
     * @param args        the arguments to pass to the constructor
     * @return the new instance; never {@code null}
     * @see #newInstance(Class, Object...)
     */
    @SneakyThrows
    private static <T> T newInstance(Constructor<T> constructor, Object... args) {
        checkNotNull(constructor, "Constructor must not be null");
        return makeAccessible(constructor).newInstance(args);
    }

    @SuppressWarnings("deprecation") // "AccessibleObject.isAccessible()" is deprecated in Java 9
    private static <T extends AccessibleObject> T makeAccessible(T object) {
        if (!object.isAccessible()) {
            object.setAccessible(true);
        }
        return object;
    }
}
