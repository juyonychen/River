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
import com.river.engine.formats.types.values.StringValue;
import com.river.engine.ast.Operand;
import lombok.EqualsAndHashCode;

/**
 * A operand which stands for a str constant
 */
@EqualsAndHashCode(callSuper = false)
public class ColumnOperand implements Operand, AliasOperand {
    private static final long serialVersionUID = 1478977541533143667L;

    private final String columnName;

    public ColumnOperand(String columnName) {
        validateString(columnName);
        this.columnName = columnName;
    }


    @Override
    public Value calculate(DataContext message, RuleContext context) {
       return calculate(message);
    }



    @Override
    public Value calculate(DataContext context) {
        return new StringValue("");
    }


    @Override
    public String toString() {
        return  columnName;
    }

    @Override
    public String getName(){
        return columnName;
    }
}
