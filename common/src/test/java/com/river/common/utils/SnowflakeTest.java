/*
 *
 *
 *
 *
 *
 */
package com.river.common.utils;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 *
 */
@Slf4j
@SuppressWarnings({"squid:S1192", "squid:S00100", "squid:S2925"})
class SnowflakeTest {

    @Test
    @SneakyThrows
    @DisplayName("id generation multiple times")
    void idGenerationMultipleTimes() {
        int times = 0, maxTimes = 1000;
        Snowflake sequence = new Snowflake(0, 0);
        for (int i = 0; i < maxTimes; i++) {
            long id = sequence.nextId();
            if (id % 2 == 0) {
                times++;
            }
            Thread.sleep(10);
        }
        log.debug("偶数:" + times + ",奇数:" + (maxTimes - times) + "!");
    }
}
