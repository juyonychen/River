
package com.river.engine.ast.operands.calculate;


import com.river.engine.formats.types.Value;
import com.river.engine.ast.Operand;
import com.river.engine.context.DataContext;
import com.river.engine.context.RuleContext;
import com.river.engine.grammar.RuleSQLParser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.Objects;

/**
 * The operand which wraps a inner operand
 *
 */
@Getter
@AllArgsConstructor
public class ParenthesisOperand implements Operand {
    private static final long serialVersionUID = -5909255881243092867L;

    @NonNull
    private final Operand innerOperand;

    @Override
    public Value calculate(DataContext context) {
        return innerOperand.calculate(context);
    }

    @Override
    public Value calculate(DataContext message, RuleContext context) {
        return innerOperand.calculate(message);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Operand)) {
            return false;
        } else {
            if (obj instanceof ParenthesisOperand) {
                return Objects.equals(((ParenthesisOperand) obj).innerOperand, innerOperand);
            } else {
                return Objects.equals(obj, innerOperand);
            }
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(RuleSQLParser.LBRACE, innerOperand);
    }

    @Override
    public String toString() {
        return "(" + innerOperand.toString() + ")";
    }
}
