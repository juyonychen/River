/*
 *
 *
 *
 *
 *
 */
package com.river.engine.ast.operands.operand;


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
public class StringOperand implements Operand {
    private static final long serialVersionUID = 1478977541533143667L;

    private final StringValue value;

    public StringOperand(String value) {
        validateString(value);
        this.value = new StringValue(removeQuote(value));
    }

    /**
     * Antlr 4 would omit the surrounded white space based on lexer definition,
     * we only care about the first & last char.
     */
    private String removeQuote(String str) {
        final int lastIndex = str.length() - 1;
        if ((str.charAt(0) == '\'' && str.charAt(lastIndex) == '\'')
            || (str.charAt(0) == '"' && str.charAt(lastIndex) == '"')) {

            return str.substring(1, lastIndex);
        } else {
            return str;
        }
    }

    @Override
    public Value calculate(DataContext message, RuleContext context) {
        return value;
    }

    @Override
    public String toString() {
        return  value.toString();
    }

    @Override
    public Value calculate(DataContext context) {
        return value;
    }
}
