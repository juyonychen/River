/*
 *
 *
 *
 *
 *
 */
package com.river.engine.ast.operands.operand;


import com.river.engine.context.MetricContext;
import com.river.engine.context.RuleContext;
import com.river.engine.formats.types.Value;
import com.river.engine.ast.Operand;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * The metric definition for the final select parts
 *
 *
 * @version 1.0.0, 2018-05-31 21:16
 * @since 1.0.0, 2018-05-31 21:16
 */
@Getter
@EqualsAndHashCode(callSuper = false)
public class NamingOperand implements Operand {
    private static final long serialVersionUID = -9040411897781735426L;

    private final String metricName;

    private final Operand metricValue;

    private final boolean hasAs;

    public NamingOperand(String metricName, Operand metricValue) {
        this.metricName = metricName;
        this.metricValue = metricValue;
        this.hasAs = true;
    }

    @Override
    public Value calculate(MetricContext message, RuleContext context) {
        return calculate(message);
    }
    @Override
    public Value calculate(MetricContext context) {
        return metricValue.calculate(context);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(metricValue.toString());
        if (hasAs) {
            builder.append(" AS ");
            builder.append(metricName);
        }

        return builder.toString();
    }
}
