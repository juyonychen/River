/*
 *
 *
 *
 *
 *
 */
package com.river.common.time;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 */
@SuppressWarnings({"squid:S1192", "squid:S00100"})
class SystemClockTest {

    @AfterAll
    static void cleanupClass() {
        SystemClock.startClockUpdateThread();
    }

    @BeforeEach
    void setup() {
        SystemClock.stopClockUpdateThread();
    }

    @Test
    void accurateTimeNanosTest() {
        long baseTime = System.nanoTime();
        assertTrue(SystemClock.accurateTimeNanos() >= baseTime);
    }

    @Test
    void lastKnownTimeNanosTest() {
        // verify clock is not updating
        long before = SystemClock.lastKnownTimeNanos();

        TimeTestUtils.blockTillClockAdvances();

        // update clock
        long newTime;
        assertTrue((newTime = SystemClock.accurateTimeNanos()) > before);
        // verify we get the new time again
        assertEquals(newTime, SystemClock.lastKnownTimeNanos());
    }

    @Test
    void startClockUpdateThreadTwiceTest() {
        SystemClock.startClockUpdateThread();

        assertNotNull(SystemClock.clockUpdater);

        Runnable updater = SystemClock.clockUpdater;

        SystemClock.startClockUpdateThread();

        // should point to the same reference
        assertSame(updater, SystemClock.clockUpdater);
    }

    @Test
    void lastKnownForwardProgressingMillisTest() {
        long timeSinceClockStartMillis = SystemClock.lastKnownForwardProgressingMillis();
        assertTrue(timeSinceClockStartMillis >= 0);
        assertTrue(timeSinceClockStartMillis < 1000 * 60 * 15); // less than 15 min
    }

    @Test
    void accurateForwardProgressingMillisTest() {
        final long timeSinceClockStartMillis = SystemClock.accurateForwardProgressingMillis();
        assertTrue(timeSinceClockStartMillis >= 0);
        assertTrue(timeSinceClockStartMillis < 1000 * 60 * 15); // less than 15 min

        new TestCondition(() -> SystemClock.accurateForwardProgressingMillis() > timeSinceClockStartMillis)
            .blockTillTrue(200);
    }

    @Test
    void lastKnownForwardProgressingMillisAccurateTimeUpdateTest() {
        // verify clock is not updating
        long before = SystemClock.lastKnownForwardProgressingMillis();

        TimeTestUtils.blockTillClockAdvances();

        // update clock
        long newTime = -1;
        assertTrue((newTime = SystemClock.accurateForwardProgressingMillis()) > before);
        // verify we get the new time again
        assertEquals(newTime, SystemClock.lastKnownForwardProgressingMillis());
    }

    @Test
    void automaticAlwaysProgressingUpdateTest() {
        SystemClock.startClockUpdateThread();
        final long before = SystemClock.lastKnownForwardProgressingMillis();

        new TestCondition(() -> SystemClock.lastKnownForwardProgressingMillis() > before).blockTillTrue(1000);
    }

    @Test
    void accurateTimeMillisTest() {
        final long startTime = SystemClock.accurateTimeMillis();

        new TestCondition(() -> SystemClock.accurateTimeMillis() > startTime).blockTillTrue(200);
    }

    @Test
    void lastKnownTimeMillisTest() {
        // verify clock is not updating
        long before = SystemClock.lastKnownTimeMillis();

        TimeTestUtils.blockTillClockAdvances();

        // update clock
        long newTime;
        assertTrue((newTime = SystemClock.accurateTimeMillis()) > before);
        // verify we get the new time again
        assertEquals(newTime, SystemClock.lastKnownTimeMillis());
    }

    @Test
    void automaticUpdateTest() {
        final long before = SystemClock.lastKnownTimeMillis();

        SystemClock.startClockUpdateThread();

        new TestCondition(() -> SystemClock.lastKnownTimeMillis() > before).blockTillTrue(1000);
    }
}
