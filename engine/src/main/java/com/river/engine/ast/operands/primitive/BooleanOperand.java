
package com.river.engine.ast.operands.primitive;


import com.river.engine.ast.Operand;
import com.river.common.exception.TypeCastException;
import com.river.common.utils.TypeUtils;
import com.river.engine.context.DataContext;
import com.river.engine.context.RuleContext;
import com.river.engine.formats.types.Value;
import com.river.engine.formats.types.values.BooleanValue;

import java.util.Map;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * A value operand which supports the boolean
 *
 */
public class BooleanOperand implements Operand {
    private static final long serialVersionUID = -6701297284736081248L;

    public static final BooleanOperand TRUE_OPERAND = new BooleanOperand(TRUE);
    public static final BooleanOperand FALSE_OPERAND = new BooleanOperand(FALSE);

    private final boolean result;

    private BooleanOperand(boolean booleanValue) {
        this.result = booleanValue;
    }


    @Override
    public Value calculate(DataContext message, Map<String,String> context) {
        return BooleanValue.valueOf(result);
    }

    @Override
    public Value calculate(DataContext context) {
        return BooleanValue.valueOf(result);
    }

    public static BooleanOperand valueOf(String boolStr) {
        try {
            Boolean booleanValue = TypeUtils.castToBoolean(boolStr);
            if (booleanValue == null) {
                return FALSE_OPERAND;
            } else {
                return booleanValue ? TRUE_OPERAND : FALSE_OPERAND;
            }
        } catch (TypeCastException e) {// NOSONAR No need to log
            return FALSE_OPERAND;
        }
    }

    @Override
    public String toString() {
        return String.valueOf(result);
    }
}
