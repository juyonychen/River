
package com.river.engine.ast.operands.primitive;


import com.river.common.utils.TypeUtils;
import com.river.engine.ast.Operand;
import com.river.engine.context.DataContext;
import com.river.engine.formats.types.Value;
import com.river.engine.formats.types.values.NumberValue;
import lombok.EqualsAndHashCode;

import java.util.Map;

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
    public Value calculate(DataContext context) {
        return number;
    }

    @Override
    public Value calculate(DataContext message, Map<String,String> context) {
        return number;
    }

    @Override
    public String toString() {
        return number.toString();
    }
}
