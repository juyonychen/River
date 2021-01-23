
package com.river.common.log;


import javax.annotation.concurrent.NotThreadSafe;
import java.io.Serializable;

/**
 * A random generated for identify if the user could log
 * @version 1.0.0, 2018-01-24 12:02
 * @since 1.0.0, 2018-01-23 17:20
 */
@NotThreadSafe
class RandomPrinter implements Serializable {
    private static final long serialVersionUID = -540510991019379075L;

    private final Sampler sampler;
    private final boolean autoRefresh;
    private volatile boolean isPrint;

    RandomPrinter(long printRate, boolean autoRefresh) {
        this.sampler = getSampler(printRate);
        this.autoRefresh = autoRefresh;
        this.isPrint = sampler.select();
    }

    boolean couldPrint() {
        if (autoRefresh) {
            isPrint = sampler.select();
        }
        return isPrint;
    }

    public void refresh() {
        if (autoRefresh) {
            throw new IllegalStateException("No need to call refresh for a auto refresh printer");
        }
        isPrint = sampler.select();
    }

    private Sampler getSampler(long samplerRate) {
        if (samplerRate == 0) {
            return new Sampler.NeverSampler();
        }
        return Sampler.create(1.0 / samplerRate * 100);
    }
}
