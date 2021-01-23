
package com.river.common.time;

import java.time.Clock;

/**
 * Generic tools to be used in time based unit testing.
 *
 *
 * @version 1.0.0, 2018-03-26 14:27
 * @since 1.0.0, 2018-03-26 14:27
 */
public class TimeTestUtils {

    /**
     * Since sleeps are sometimes necessary, this makes an easy way to ignore InterruptedException's.
     *
     * @param time time in milliseconds to make the thread to sleep
     */
    public static void sleep(long time) {
        try {
            Thread.sleep(time); // NOSONAR A better sleep code utils
        } catch (InterruptedException e) {
            // reset interrupted status
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Blocks until the System clock advances at least 1 millisecond.  This will also ensure that
     * the {@link Clock} class's representation of time has advanced.
     */
    public static void blockTillClockAdvances() {
        new TestCondition() {
            private static final short POLL_INTERVAL_IN_MS = 1;

            private final long startTime = SystemClock.accurateTimeMillis();
            private final long alwaysProgressingStartTime = SystemClock.accurateForwardProgressingMillis();

            @Override
            public boolean get() {
                return SystemClock.accurateTimeMillis() > startTime &&
                    SystemClock.accurateForwardProgressingMillis() > alwaysProgressingStartTime;
            }

            @Override
            public void blockTillTrue() {
                blockTillTrue(DEFAULT_TIMEOUT, POLL_INTERVAL_IN_MS);
            }
        }.blockTillTrue();
    }
}
