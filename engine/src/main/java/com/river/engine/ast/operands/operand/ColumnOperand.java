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
import com.river.engine.formats.types.values.StringValue;
import com.river.engine.ast.Operand;
import lombok.EqualsAndHashCode;

/**
 * A operand which stands for a str constant
 */
@EqualsAndHashCode(callSuper = false)
public class ColumnOperand implements Operand {
    private static final long serialVersionUID = 1478977541533143667L;

    private final String value;

    public ColumnOperand(String value) {
        validateString(value);
        this.value = value;
    }


    @Override
    public Value calculate(MetricContext message, RuleContext context) {
       return calculate(message);
    }

    @Override
    public String toString() {
        return  value;
    }

    @Override
    public Value calculate(MetricContext context) {
        return new StringValue("");
    }
}
