
package com.river.engine.ast.expressions.compare;


import com.river.engine.ast.Operand;
import com.river.engine.ast.expressions.base.AbstractCompareExpression;
import com.river.engine.context.DataContext;
import com.river.engine.formats.types.Value;
import com.river.engine.grammar.RuleSQLParser;

import java.util.Objects;

/**
 * A equals expression for two operands
 */
public class EqualsExpression extends AbstractCompareExpression {
    private static final long serialVersionUID = -3454354507303276202L;

    public EqualsExpression(Operand leftOperand, Operand rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public boolean evaluate(DataContext context) {
        Value left = leftValue(context);
        Value right = rightValue(context);

        return valueEqualsCompare(left, right);
    }

    private boolean valueEqualsCompare(Value leftValue, Value rightValue) {
        return Objects.equals(leftValue, rightValue);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        } else {
            return Objects.equals(((EqualsExpression) obj).leftOperand, leftOperand)
                && Objects.equals(((EqualsExpression) obj).rightOperand, rightOperand);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftOperand, RuleSQLParser.EQ, rightOperand);
    }

    @Override
    public String toString() {
        return leftOperand.toString() + " = " + rightOperand.toString();
    }

    public String toString(boolean equals) {
        String token = equals ? " = " : " != ";
        return leftOperand.toString() + token + rightOperand.toString();
    }
}
