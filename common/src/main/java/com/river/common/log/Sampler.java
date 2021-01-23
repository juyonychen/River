
package com.river.common.log;

import javax.annotation.concurrent.ThreadSafe;
import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * A Sampler inspired by Twitter Common
 *
 *
 * @version 1.0.0, 2018-03-23 13:03
 * @link https://github.com/twitter/commons/blob/master/src/java/com/twitter/common/util/Sampler.java
 * @since 1.0.0, 2018-03-23 13:03
 */
@ThreadSafe
public class Sampler implements Serializable {
    private static final long serialVersionUID = 1664209040806975733L;

    private static final Double ALWAYS = 100d;
    private static final Double NEVER = 0d;

    private double threshold;

    private Sampler() {
        // A constructor for overriding
    }

    /**
     * @param selectPercent 采样率，在0-100 之间，可以有小数位
     */
    private Sampler(double selectPercent) {
        checkArgument((selectPercent >= 0) && (selectPercent <= 100),
            "Invalid selectPercent value: " + selectPercent);

        this.threshold = selectPercent / 100;
    }

    /**
     * 优化的创建函数，如果为 <=0 或 >=100 时，返回更直接的采样器
     */
    public static Sampler create(Double selectPercent) {
        if (selectPercent >= ALWAYS) {
            return new AlwaysSampler();
        } else if (selectPercent <= NEVER) {
            return new NeverSampler();
        } else {
            return new Sampler(selectPercent);
        }
    }

    /**
     * 判断当前请求是否命中采样
     */
    public boolean select() {
        return ThreadLocalRandom.current().nextDouble() < threshold;
    }

    /**
     * 采样率为100时，总是返回true
     */
    public static class AlwaysSampler extends Sampler {
        private static final long serialVersionUID = -6832487822343243670L;

        @Override
        public boolean select() {
            return true;
        }
    }

    /**
     * 采样率为0时，总是返回false
     */
    public static class NeverSampler extends Sampler {
        private static final long serialVersionUID = 6757997672236982905L;

        @Override
        public boolean select() {
            return false;
        }
    }
}
