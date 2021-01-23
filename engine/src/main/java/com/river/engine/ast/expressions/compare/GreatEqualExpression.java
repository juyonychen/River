
package com.river.engine.ast.expressions.compare;


import com.river.engine.ast.Operand;
import com.river.engine.ast.expressions.base.AbstractCompareExpression;
import com.river.engine.context.MetricContext;
import com.river.engine.grammar.RuleSQLParser;

import java.util.Objects;

/**
 * A great equals expression for two operands
 *
 */
public class GreatEqualExpression extends AbstractCompareExpression {
    private static final long serialVersionUID = 2014528827770342891L;

    public GreatEqualExpression(Operand leftOperand, Operand rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public boolean evaluate(MetricContext context) {
        double leftNumber = leftNumber(context);
        double rightNumber = rightNumber(context);

        return Double.compare(leftNumber, rightNumber) >= 0 && !Double.isNaN(leftNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        } else {
            return Objects.equals(((GreatEqualExpression) obj).leftOperand, leftOperand)
                && Objects.equals(((GreatEqualExpression) obj).rightOperand, rightOperand);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftOperand, RuleSQLParser.GTEQ, rightOperand);
    }

    @Override
    public String toString() {
        return leftOperand.toString() + " >= " + rightOperand.toString();
    }
}
