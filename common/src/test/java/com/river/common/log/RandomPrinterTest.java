
package com.river.common.log;


import org.junit.Test;
import org.junit.jupiter.api.RepeatedTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;
import static org.junit.jupiter.api.Assertions.assertThrows;
/**
 *
 */

class RandomPrinterTest {

    @RepeatedTest(10)
    void createAlwaysLogPrinter() {
        RandomPrinter printer = new RandomPrinter(1, true);
        assertThat(printer.couldPrint()).isTrue();
    }

    @RepeatedTest(10)
    void createNeverLogPrinter() {
        RandomPrinter printer = new RandomPrinter(0, true);
        assertThat(printer.couldPrint()).isFalse();

        RandomPrinter printer1 = new RandomPrinter(-1, true);
        assertThat(printer1.couldPrint()).isFalse();
    }

    @Test
    void logRateCouldDefineWhetherToRefresh() {
        RandomPrinter printer = new RandomPrinter(100, false);
        boolean result = printer.couldPrint();

        for (int i = 0; i < 1000; i++) {
            assertThat(printer.couldPrint()).isEqualTo(result);
        }
    }

    @Test
    void logShouldBasedOnLogRate() {
        RandomPrinter printer = new RandomPrinter(100, true);

        int totalPassCount = 0;
        for (int i = 0; i < 10000; i++) {
            if (printer.couldPrint()) {
                totalPassCount++;
            }
        }

        assertThat(totalPassCount).isBetween(50, 200);
    }
}
