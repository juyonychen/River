/*
 *
 *
 *
 *
 *
 */
package com.river.engine.ast.operands.base;

import com.river.engine.context.DataContext;
import com.river.engine.context.RuleContext;
import com.river.engine.formats.exception.ValueTypeNotSupportedException;
import com.river.engine.formats.types.Value;
import com.river.engine.formats.types.utils.ValueConstants;
import com.river.engine.formats.types.utils.ValueConverter;
import com.river.engine.formats.types.values.NumberValue;
import com.river.engine.formats.types.values.StringValue;
import com.river.engine.ast.Operand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;


@Getter
@AllArgsConstructor
public abstract class AbstractBinaryOperand implements Operand {
    private static final long serialVersionUID = -4360123224144649738L;

    @NonNull
    protected final Operand leftOperand;

    @NonNull
    protected final Operand rightOperand;

    @Override
    public Value calculate(DataContext message, RuleContext context){
        Value leftValue = leftOperand.calculate(message, context);
        Value rightValue = rightOperand.calculate(message, context);

        return calculate(leftValue, rightValue);
    }

    @Override
    public Value calculate(DataContext context) {
        Value leftValue = leftOperand.calculate(context);
        Value rightValue = rightOperand.calculate(context);

        return calculate(leftValue, rightValue);
    }

    private Value calculate(Value leftValue, Value rightValue) {
        NumberValue result;

        if (leftValue instanceof StringValue) {
            double left = ((StringValue) leftValue).numberValue();

            if (rightValue instanceof StringValue) {
                double right = ((StringValue) rightValue).numberValue();
                // Both string should create a value
                result = new NumberValue(operator().operate(left, right));
            } else if (rightValue instanceof NumberValue) {
                double right = ((NumberValue) rightValue).getNumber();
                result = new NumberValue(operator().operate(left, right));
            } else {
                throw new ValueTypeNotSupportedException("The value type {} couldn't get the numeric value", rightValue.getClass());
            }
        } else if (leftValue instanceof NumberValue ) {
            double left = ((NumberValue) leftValue).getNumber();
            double right = ValueConverter.numberValue(rightValue);
            result = new NumberValue(operator().operate(left, right));
        }
        else {
            throw new ValueTypeNotSupportedException("The value type {} couldn't get the numeric value", leftValue.getClass());
        }

        // Wrong double calculate such as divide zero should be convert to NaN
        if (Double.isNaN(result.getNumber()) || Double.isInfinite(result.getNumber())) {
            return ValueConstants.NAN_VALUE;
        } else {
            return result;
        }
    }

    protected abstract ValueCalculateOperator operator();

    /**
     * A common functional interface for how to calculate the numeric {@link Value}
     *
     */
    @FunctionalInterface
    public interface ValueCalculateOperator {

        double operate(double left, double right);
    }
}
