
package com.river.engine.ast.expressions.base;


import com.river.engine.ast.Expression;
import com.river.engine.ast.Operand;
import com.river.engine.context.MetricContext;
import com.river.engine.formats.types.Value;
import com.river.engine.formats.types.utils.ValueConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Join two operands into one expression.
 *
 *
 * @version 1.0.0, 2018-06-01 05:26
 * @since 1.0.0, 2018-06-01 05:26
 */
@Getter
@AllArgsConstructor
public abstract class AbstractCompareExpression implements Expression {
    private static final long serialVersionUID = 1585217044400322079L;

    protected final Operand leftOperand;

    protected final Operand rightOperand;

    protected Value leftValue(MetricContext context) {
        return leftOperand.calculate(context);
    }

    protected Value rightValue(MetricContext context) {
        return rightOperand.calculate(context);
    }

    protected double leftNumber(MetricContext context) {
        Value value = leftValue(context);
        return ValueConverter.numberValue(value);
    }

    protected double rightNumber(MetricContext context) {
        Value value = rightValue(context);
        return ValueConverter.numberValue(value);
    }
}
