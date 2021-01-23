
package com.river.engine.ast.operands.primitive;

import com.river.engine.formats.types.Value;
import com.river.engine.ast.Operand;
import com.river.common.utils.TypeUtils;
import com.river.engine.context.MetricContext;
import com.river.engine.context.RuleContext;
import com.river.engine.formats.types.values.NumberValue;
import lombok.EqualsAndHashCode;

/**
 * A operand which stores long number
 */
@EqualsAndHashCode(callSuper = false)
public class LongOperand implements Operand {
    private static final long serialVersionUID = -4730659597191491145L;

    private final NumberValue number;

    public LongOperand(String numberStr) {
        validateString(numberStr);
        this.number = new NumberValue(TypeUtils.castToLong(numberStr));
    }



    @Override
    public Value calculate(MetricContext message, RuleContext context) {
        return number;
    }

    @Override
    public Value calculate(MetricContext context) {
        return number;
    }

    @Override
    public String toString() {
        return number.toString();
    }
}
