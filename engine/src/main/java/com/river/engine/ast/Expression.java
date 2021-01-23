
package com.river.engine.ast;

import com.river.engine.context.MetricContext;

import java.io.Serializable;

/**
 * The boolean comparator for operands
 *
 *
 * @version 1.0.0, 2018-06-01 05:06
 * @since 1.0.0, 2018-06-01 05:06
 */
public interface Expression extends Serializable {


    /**
     * Evaluate if this metric message should exact in ETL.
     *
     * @param context    A raw metric context
     */
    boolean evaluate(MetricContext context);
}
