
package com.river.common.log;


import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.Marker;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.mock;

/**
 * This class is mainly used for improve the test coverage
 *
 *
 */

class SampleLogImplTest {

    private SampleLogImpl sampleLog = new SampleLogImpl(mock(Logger.class), 1, true);

    @Test
    void mockingAllLogMethod() {
        assertAll("",
            () -> sampleLog.getName(),
            () -> sampleLog.isTraceEnabled(),
            () -> sampleLog.trace(getLoggerText()),
            () -> sampleLog.trace(getLoggerText(), 1),
            () -> sampleLog.trace(getLoggerText(), 1, 2),
            () -> sampleLog.trace(getLoggerText(), 1, 2, 3),
            () -> sampleLog.trace(getLoggerText(), new RuntimeException()),
            () -> sampleLog.isTraceEnabled(mock(Marker.class)),
            () -> sampleLog.trace(mock(Marker.class), getLoggerText()),
            () -> sampleLog.trace(mock(Marker.class), getLoggerText(), 1),
            () -> sampleLog.trace(mock(Marker.class), getLoggerText(), 1, 2),
            () -> sampleLog.trace(mock(Marker.class), getLoggerText(), 1, 2, 3),
            () -> sampleLog.trace(mock(Marker.class), getLoggerText(), new RuntimeException()),
            () -> sampleLog.isDebugEnabled(),
            () -> sampleLog.debug(getLoggerText()),
            () -> sampleLog.debug(getLoggerText(), 1),
            () -> sampleLog.debug(getLoggerText(), 1, 2),
            () -> sampleLog.debug(getLoggerText(), 1, 2, 3),
            () -> sampleLog.debug(getLoggerText(), new RuntimeException()),
            () -> sampleLog.isDebugEnabled(mock(Marker.class)),
            () -> sampleLog.debug(mock(Marker.class), getLoggerText()),
            () -> sampleLog.debug(mock(Marker.class), getLoggerText(), 1),
            () -> sampleLog.debug(mock(Marker.class), getLoggerText(), 1, 2),
            () -> sampleLog.debug(mock(Marker.class), getLoggerText(), 1, 2, 3),
            () -> sampleLog.debug(mock(Marker.class), getLoggerText(), new RuntimeException()),
            () -> sampleLog.isInfoEnabled(),
            () -> sampleLog.info(getLoggerText()),
            () -> sampleLog.info(getLoggerText(), 1),
            () -> sampleLog.info(getLoggerText(), 1, 2),
            () -> sampleLog.info(getLoggerText(), 1, 2, 3),
            () -> sampleLog.info(getLoggerText(), new RuntimeException()),
            () -> sampleLog.isInfoEnabled(mock(Marker.class)),
            () -> sampleLog.info(mock(Marker.class), getLoggerText()),
            () -> sampleLog.info(mock(Marker.class), getLoggerText(), 1),
            () -> sampleLog.info(mock(Marker.class), getLoggerText(), 1, 2),
            () -> sampleLog.info(mock(Marker.class), getLoggerText(), 1, 2, 3),
            () -> sampleLog.info(mock(Marker.class), getLoggerText(), new RuntimeException()),
            () -> sampleLog.isWarnEnabled(),
            () -> sampleLog.warn(getLoggerText()),
            () -> sampleLog.warn(getLoggerText(), 1),
            () -> sampleLog.warn(getLoggerText(), 1, 2),
            () -> sampleLog.warn(getLoggerText(), 1, 2, 3),
            () -> sampleLog.warn(getLoggerText(), new RuntimeException()),
            () -> sampleLog.isWarnEnabled(mock(Marker.class)),
            () -> sampleLog.warn(mock(Marker.class), getLoggerText()),
            () -> sampleLog.warn(mock(Marker.class), getLoggerText(), 1),
            () -> sampleLog.warn(mock(Marker.class), getLoggerText(), 1, 2),
            () -> sampleLog.warn(mock(Marker.class), getLoggerText(), 1, 2, 3),
            () -> sampleLog.warn(mock(Marker.class), getLoggerText(), new RuntimeException()),
            () -> sampleLog.isErrorEnabled(),
            () -> sampleLog.error(getLoggerText()),
            () -> sampleLog.error(getLoggerText(), 1),
            () -> sampleLog.error(getLoggerText(), 1, 2),
            () -> sampleLog.error(getLoggerText(), 1, 2, 3),
            () -> sampleLog.error(getLoggerText(), new RuntimeException()),
            () -> sampleLog.isErrorEnabled(mock(Marker.class)),
            () -> sampleLog.error(mock(Marker.class), getLoggerText()),
            () -> sampleLog.error(mock(Marker.class), getLoggerText(), 1),
            () -> sampleLog.error(mock(Marker.class), getLoggerText(), 1, 2),
            () -> sampleLog.error(mock(Marker.class), getLoggerText(), 1, 2, 3),
            () -> sampleLog.error(mock(Marker.class), getLoggerText(), new RuntimeException())
        );
    }

    private String getLoggerText() {
        return randomAlphabetic(10) + "{}{}{}{}{}";
    }
}
