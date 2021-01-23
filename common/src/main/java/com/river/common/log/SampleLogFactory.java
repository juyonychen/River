
package com.river.common.log;

import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Factory for building the SampleLog, this class hides the {@link SampleLogImpl} implementation.
 *
 *
 * @version 1.0.0, 2018-01-24 12:02
 * @since 1.0.0, 2018-01-23 17:20
 */
@UtilityClass
public class SampleLogFactory {

    public static SampleLog getLogger(Class<?> clazz) {
        return new SampleLogImpl(createLogger(clazz), 10000, true);
    }

    public static SampleLog getLogger(Class<?> clazz, long logRate) {
        return new SampleLogImpl(createLogger(clazz), logRate, true);
    }

    public static SampleLog getLogger(Class<?> clazz, long logRate, boolean autoRefresh) {
        return new SampleLogImpl(createLogger(clazz), logRate, autoRefresh);
    }

    public static SampleLog getLogger(String name) {
        return new SampleLogImpl(createLogger(name), 10000, true);
    }

    public static SampleLog getLogger(String name, long logRate) {
        return new SampleLogImpl(createLogger(name), logRate, true);
    }

    public static SampleLog getLogger(String name, long logRate, boolean autoRefresh) {
        return new SampleLogImpl(createLogger(name), logRate, autoRefresh);
    }

    private static Logger createLogger(String name) {
        return LoggerFactory.getLogger(name);
    }

    private static Logger createLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }
}
