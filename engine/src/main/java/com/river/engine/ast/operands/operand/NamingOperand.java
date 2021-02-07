/*
 *
 *
 *
 *
 *
 */
package com.river.engine.ast.operands.operand;


import com.river.engine.ast.Operand;
import com.river.engine.context.DataContext;
import com.river.engine.formats.types.Value;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Map;

/**
 * The metric definition for the final select parts
 * @version 1.0.0, 2018-05-31 21:16
 * @since 1.0.0, 2018-05-31 21:16
 */
@Getter
@EqualsAndHashCode(callSuper = false)
public class NamingOperand implements Operand{
    private static final long serialVersionUID = -9040411897781735426L;

    private final String namingName;

    private final Operand namingOperand;

    public NamingOperand(String namingName, Operand namingOperand) {
        this.namingName = namingName;
        this.namingOperand = namingOperand;
    }

    @Override
    public Value calculate(DataContext message, Map<String,String> context) {
        return calculate(message);
    }

    @Override
    public Value calculate(DataContext context) {
        return namingOperand.calculate(context);
    }

    @Override
    public String toString() {
        return namingName;
    }

}
