
package com.river.engine.ast.operands.primitive;


import com.river.engine.ast.Operand;
import com.river.common.utils.TypeUtils;
import com.river.engine.context.MetricContext;
import com.river.engine.context.RuleContext;
import com.river.engine.formats.types.Value;
import com.river.engine.formats.types.values.NumberValue;
import lombok.EqualsAndHashCode;

/**
 * A operand which stores float number
 *
 */
@EqualsAndHashCode(callSuper = false)
public class FloatOperand implements Operand {
    private static final long serialVersionUID = -1613542395347599168L;

    private final NumberValue number;

    public FloatOperand(String numberStr) {
        validateString(numberStr);
        this.number = new NumberValue(TypeUtils.castToDouble(numberStr));
    }


    @Override
    public Value calculate(MetricContext context) {
        return number;
    }

    @Override
    public Value calculate(MetricContext message, RuleContext context) {
        return number;
    }

    @Override
    public String toString() {
        return number.toString();
    }
}
