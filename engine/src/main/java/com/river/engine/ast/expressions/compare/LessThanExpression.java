/*
 *
 *
 *
 *
 *
 */
package com.river.engine.ast.expressions.compare;


import com.river.engine.ast.Operand;
import com.river.engine.ast.expressions.base.AbstractCompareExpression;
import com.river.engine.context.DataContext;
import com.river.engine.grammar.RuleSQLParser;

import java.util.Objects;

/**
 * A less than expression for two operands
 *
 */
public class LessThanExpression extends AbstractCompareExpression {
    private static final long serialVersionUID = 1135798964766638636L;

    public LessThanExpression(Operand leftOperand, Operand rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public boolean evaluate(DataContext context) {
        double leftNumber = leftNumber(context);
        double rightNumber = rightNumber(context);

        return Double.compare(leftNumber, rightNumber) < 0 && !Double.isNaN(rightNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        } else {
            return Objects.equals(((LessThanExpression) obj).leftOperand, leftOperand)
                && Objects.equals(((LessThanExpression) obj).rightOperand, rightOperand);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftOperand, RuleSQLParser.LT, rightOperand);
    }

    @Override
    public String toString() {
        return leftOperand.toString() + " < " + rightOperand.toString();
    }
}
