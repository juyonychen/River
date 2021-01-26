/*
 *
 *
 *
 *
 *
 */
package com.river.engine.ast.operands.operand;


import com.river.engine.ast.AliasOperand;
import com.river.engine.context.DataContext;
import com.river.engine.context.RuleContext;
import com.river.engine.formats.types.Value;
import com.river.engine.ast.Operand;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * The metric definition for the final select parts
 * @version 1.0.0, 2018-05-31 21:16
 * @since 1.0.0, 2018-05-31 21:16
 */
@Getter
@EqualsAndHashCode(callSuper = false)
public class NamingOperand implements Operand, AliasOperand {
    private static final long serialVersionUID = -9040411897781735426L;

    private final String namingName;

    private final Operand namingOperand;


    public NamingOperand(String namingName, Operand namingOperand) {
        this.namingName = namingName;
        this.namingOperand = namingOperand;
    }

    @Override
    public Value calculate(DataContext message, RuleContext context) {
        return calculate(message);
    }

    @Override
    public Value calculate(DataContext context) {
        return namingOperand.calculate(context);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(namingOperand.toString());
        builder.append(" AS ");
        builder.append(namingName);
        return builder.toString();
    }


    @Override
    public String getName(){
        return namingName;
    }
}
