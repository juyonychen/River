
package com.river.common.log;

import org.slf4j.Logger;
import org.slf4j.Marker;

/**
 * A implementation of {@link SampleLog}
 *
 *
 * @version 1.0.0, 2018-01-24 12:02
 * @since 1.0.0, 2018-01-23 17:20
 */
public class SampleLogImpl implements SampleLog {
    private static final long serialVersionUID = 8375574776364800875L;

    private final Logger innerLogger; // NOSONAR SLF4J is already serializable, see https://www.slf4j.org/faq.html#declared_static
    private final RandomPrinter randomPrinter;

    public SampleLogImpl(Logger innerLogger, long logRate, boolean autoRefresh) {
        this.innerLogger = innerLogger;
        this.randomPrinter = new RandomPrinter(logRate, autoRefresh);
    }

    @Override
    public String getName() {
        return innerLogger.getName();
    }

    @Override
    public boolean isTraceEnabled() {
        return innerLogger.isTraceEnabled();
    }

    @Override
    public void trace(String msg) {
        if (randomPrinter.couldPrint()) {
            innerLogger.trace(msg);
        }
    }

    @Override
    public void trace(String format, Object arg) {
        if (randomPrinter.couldPrint()) {
            innerLogger.trace(format, arg);
        }
    }

    @Override
    public void trace(String format, Object arg1, Object arg2) {
        if (randomPrinter.couldPrint()) {
            innerLogger.trace(format, arg1, arg2);
        }
    }

    @Override
    public void trace(String format, Object... arguments) {
        if (randomPrinter.couldPrint()) {
            innerLogger.trace(format, arguments);
        }
    }

    @Override
    public void trace(String msg, Throwable t) {
        if (randomPrinter.couldPrint()) {
            innerLogger.trace(msg, t);
        }
    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        return innerLogger.isTraceEnabled();
    }

    @Override
    public void trace(Marker marker, String msg) {
        if (randomPrinter.couldPrint()) {
            innerLogger.trace(marker, msg);
        }
    }

    @Override
    public void trace(Marker marker, String format, Object arg) {
        if (randomPrinter.couldPrint()) {
            innerLogger.trace(marker, format, arg);
        }
    }

    @Override
    public void trace(Marker marker, String format, Object arg1, Object arg2) {
        if (randomPrinter.couldPrint()) {
            innerLogger.trace(marker, format, arg1, arg2);
        }
    }

    @Override
    public void trace(Marker marker, String format, Object... argArray) {
        if (randomPrinter.couldPrint()) {
            innerLogger.trace(marker, format, argArray);
        }
    }

    @Override
    public void trace(Marker marker, String msg, Throwable t) {
        if (randomPrinter.couldPrint()) {
            innerLogger.trace(marker, msg, t);
        }
    }

    @Override
    public boolean isDebugEnabled() {
        return innerLogger.isDebugEnabled();
    }

    @Override
    public void debug(String msg) {
        if (randomPrinter.couldPrint()) {
            innerLogger.debug(msg);
        }
    }

    @Override
    public void debug(String format, Object arg) {
        if (randomPrinter.couldPrint()) {
            innerLogger.debug(format, arg);
        }
    }

    @Override
    public void debug(String format, Object arg1, Object arg2) {
        if (randomPrinter.couldPrint()) {
            innerLogger.debug(format, arg1, arg2);
        }
    }

    @Override
    public void debug(String format, Object... arguments) {
        if (randomPrinter.couldPrint()) {
            innerLogger.debug(format, arguments);
        }
    }

    @Override
    public void debug(String msg, Throwable t) {
        if (randomPrinter.couldPrint()) {
            innerLogger.debug(msg, t);
        }
    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        return innerLogger.isDebugEnabled();
    }

    @Override
    public void debug(Marker marker, String msg) {
        if (randomPrinter.couldPrint()) {
            innerLogger.debug(marker, msg);
        }
    }

    @Override
    public void debug(Marker marker, String format, Object arg) {
        if (randomPrinter.couldPrint()) {
            innerLogger.debug(marker, format, arg);
        }
    }

    @Override
    public void debug(Marker marker, String format, Object arg1, Object arg2) {
        if (randomPrinter.couldPrint()) {
            innerLogger.debug(marker, format, arg1, arg2);
        }
    }

    @Override
    public void debug(Marker marker, String format, Object... arguments) {
        if (randomPrinter.couldPrint()) {
            innerLogger.debug(marker, format, arguments);
        }
    }

    @Override
    public void debug(Marker marker, String msg, Throwable t) {
        if (randomPrinter.couldPrint()) {
            innerLogger.debug(marker, msg, t);
        }
    }

    @Override
    public boolean isInfoEnabled() {
        return innerLogger.isInfoEnabled();
    }

    @Override
    public void info(String msg) {
        if (randomPrinter.couldPrint()) {
            innerLogger.info(msg);
        }
    }

    @Override
    public void info(String format, Object arg) {
        if (randomPrinter.couldPrint()) {
            innerLogger.info(format, arg);
        }
    }

    @Override
    public void info(String format, Object arg1, Object arg2) {
        if (randomPrinter.couldPrint()) {
            innerLogger.info(format, arg1, arg2);
        }
    }

    @Override
    public void info(String format, Object... arguments) {
        if (randomPrinter.couldPrint()) {
            innerLogger.info(format, arguments);
        }
    }

    @Override
    public void info(String msg, Throwable t) {
        if (randomPrinter.couldPrint()) {
            innerLogger.info(msg, t);
        }
    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        return innerLogger.isInfoEnabled();
    }

    @Override
    public void info(Marker marker, String msg) {
        if (randomPrinter.couldPrint()) {
            innerLogger.info(marker, msg);
        }
    }

    @Override
    public void info(Marker marker, String format, Object arg) {
        if (randomPrinter.couldPrint()) {
            innerLogger.info(marker, format, arg);
        }
    }

    @Override
    public void info(Marker marker, String format, Object arg1, Object arg2) {
        if (randomPrinter.couldPrint()) {
            innerLogger.info(marker, format, arg1, arg2);
        }
    }

    @Override
    public void info(Marker marker, String format, Object... arguments) {
        if (randomPrinter.couldPrint()) {
            innerLogger.info(marker, format, arguments);
        }
    }

    @Override
    public void info(Marker marker, String msg, Throwable t) {
        if (randomPrinter.couldPrint()) {
            innerLogger.info(marker, msg, t);
        }
    }

    @Override
    public boolean isWarnEnabled() {
        return innerLogger.isWarnEnabled();
    }

    @Override
    public void warn(String msg) {
        if (randomPrinter.couldPrint()) {
            innerLogger.warn(msg);
        }
    }

    @Override
    public void warn(String format, Object arg) {
        if (randomPrinter.couldPrint()) {
            innerLogger.warn(format, arg);
        }
    }

    @Override
    public void warn(String format, Object... arguments) {
        if (randomPrinter.couldPrint()) {
            innerLogger.warn(format, arguments);
        }
    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {
        if (randomPrinter.couldPrint()) {
            innerLogger.warn(format, arg1, arg2);
        }
    }

    @Override
    public void warn(String msg, Throwable t) {
        if (randomPrinter.couldPrint()) {
            innerLogger.warn(msg, t);
        }
    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        return innerLogger.isWarnEnabled();
    }

    @Override
    public void warn(Marker marker, String msg) {
        if (randomPrinter.couldPrint()) {
            innerLogger.warn(marker, msg);
        }
    }

    @Override
    public void warn(Marker marker, String format, Object arg) {
        if (randomPrinter.couldPrint()) {
            innerLogger.warn(marker, format, arg);
        }
    }

    @Override
    public void warn(Marker marker, String format, Object arg1, Object arg2) {
        if (randomPrinter.couldPrint()) {
            innerLogger.warn(marker, format, arg1, arg2);
        }
    }

    @Override
    public void warn(Marker marker, String format, Object... arguments) {
        if (randomPrinter.couldPrint()) {
            innerLogger.warn(marker, format, arguments);
        }
    }

    @Override
    public void warn(Marker marker, String msg, Throwable t) {
        if (randomPrinter.couldPrint()) {
            innerLogger.warn(marker, msg, t);
        }
    }

    @Override
    public boolean isErrorEnabled() {
        return innerLogger.isErrorEnabled();
    }

    @Override
    public void error(String msg) {
        if (randomPrinter.couldPrint()) {
            innerLogger.error(msg);
        }
    }

    @Override
    public void error(String format, Object arg) {
        if (randomPrinter.couldPrint()) {
            innerLogger.error(format, arg);
        }
    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        if (randomPrinter.couldPrint()) {
            innerLogger.error(format, arg1, arg2);
        }
    }

    @Override
    public void error(String format, Object... arguments) {
        if (randomPrinter.couldPrint()) {
            innerLogger.error(format, arguments);
        }
    }

    @Override
    public void error(String msg, Throwable t) {
        if (randomPrinter.couldPrint()) {
            innerLogger.error(msg, t);
        }
    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        return innerLogger.isErrorEnabled();
    }

    @Override
    public void error(Marker marker, String msg) {
        if (randomPrinter.couldPrint()) {
            innerLogger.error(marker, msg);
        }
    }

    @Override
    public void error(Marker marker, String format, Object arg) {
        if (randomPrinter.couldPrint()) {
            innerLogger.error(marker, format, arg);
        }
    }

    @Override
    public void error(Marker marker, String format, Object arg1, Object arg2) {
        if (randomPrinter.couldPrint()) {
            innerLogger.error(marker, format, arg1, arg2);
        }
    }

    @Override
    public void error(Marker marker, String format, Object... arguments) {
        if (randomPrinter.couldPrint()) {
            innerLogger.error(marker, format, arguments);
        }
    }

    @Override
    public void error(Marker marker, String msg, Throwable t) {
        if (randomPrinter.couldPrint()) {
            innerLogger.error(marker, msg, t);
        }
    }

    @Override
    public void refresh() {
        randomPrinter.refresh();
    }
}
