/*
 *
 *
 *
 *
 *
 */
package com.river.common.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;

/**
 *
 */
@SuppressWarnings({"squid:S1192", "squid:S00100"})
class IdUtilsTest {

    @Test
    void everyUIDMethodCalling() {
        assertAll(
            IdUtils::randomLong,
            IdUtils::randomUUID,
            IdUtils::randomRawUUID,
            () -> IdUtils.randomBase64(10)
        );
    }
}
