
package com.river.common.log;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 */
@Slf4j
@SuppressWarnings({"squid:S1192", "squid:S00100"})
class SamplerTest {

    @Test
    void samplerExecutionRangeJudgement() {
        Sampler sampler = Sampler.create(10.5);
        int hits = 0;
        for (int i = 0; i < 10000; i++) {
            if (sampler.select()) {
                hits++;
            }
        }
        log.info("sample 10.5% in 10000 hits should close to 1050, actual is {}", hits);
        assertThat(hits).isBetween(900, 1200);

        Sampler sampler2 = Sampler.create(0.5);
        hits = 0;
        for (int i = 0; i < 10000; i++) {
            if (sampler2.select()) {
                hits++;
            }
        }
        log.info("sample 0.5% in 10000 hits should close to 50, actual is {}", hits);
        assertThat(hits).isBetween(20, 100);
    }

}

