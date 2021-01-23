/*
 *
 *
 *
 *
 *
 */
package com.river.engine.runtime.partial;


import com.river.engine.ast.Expression;
import com.river.engine.context.MetricContext;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * The filterStream for selecting desired metrics to calculate.
 *
 */
@AllArgsConstructor
@Slf4j
public class MetricFilter extends AbstractPartInfo {
    private static final long serialVersionUID = 4646803424844939963L;


    @NonNull
    private final Expression filterExpression;

    public boolean isDesiredMetric(MetricContext context) {
        try {
            return filterExpression.evaluate(context);
        } catch (Exception e) {
            log.debug("filterExpression error:{}", e);
            return false;
        }
    }

    @Override
    public String toString() {
        return "WHERE " + filterExpression.toString();
    }

    public Expression getFilterExpression(){
        return this.filterExpression;
    }
}
