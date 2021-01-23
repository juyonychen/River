
package com.river.engine.ast.operands.calculate.arithmetic;

import com.river.engine.ast.Operand;
import com.river.engine.ast.operands.base.AbstractBinaryOperand;
import com.river.engine.grammar.RuleSQLParser;

import java.util.Objects;

public class MultiplyOperand extends AbstractBinaryOperand {
    private static final long serialVersionUID = 1178186484601949346L;

    public MultiplyOperand(Operand leftOperand, Operand rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    protected ValueCalculateOperator operator() {
        return (a, b) -> a * b;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        } else {
            MultiplyOperand that = (MultiplyOperand) obj;
            return Objects.equals(that.leftOperand, leftOperand)
                && Objects.equals(that.rightOperand, rightOperand);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftOperand, RuleSQLParser.STAR, rightOperand);
    }

    @Override
    public String toString() {
        return leftOperand.toString() + " * " + rightOperand.toString();
    }
}
