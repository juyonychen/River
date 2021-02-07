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
import com.river.engine.formats.types.values.StringValue;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * A operand which stands for a str constant
 */
@EqualsAndHashCode(callSuper = false)
public class ColumnOperand implements Operand{
    private static final long serialVersionUID = 1478977541533143667L;

    private final String columnName;

    public ColumnOperand(String columnName) {
        validateString(columnName);
        this.columnName = columnName;
    }


    @Override
    public Value calculate(DataContext message, Map<String,String> context) {
       return new StringValue(context.get(columnName));
    }


    @Override
    public Value calculate(DataContext context) {
        return new StringValue(context.getStringValue(columnName));
    }


    @Override
    public String toString() {
        return  columnName;
    }

}
