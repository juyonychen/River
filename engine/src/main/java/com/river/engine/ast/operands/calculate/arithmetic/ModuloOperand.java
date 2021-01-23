
package com.river.engine.ast.operands.calculate.arithmetic;


import com.river.engine.ast.Operand;
import com.river.engine.ast.operands.base.AbstractBinaryOperand;
import com.river.engine.grammar.RuleSQLParser;

import java.util.Objects;

public class ModuloOperand extends AbstractBinaryOperand {
    private static final long serialVersionUID = 9103328682957985658L;

    public ModuloOperand(Operand leftOperand, Operand rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    protected ValueCalculateOperator operator() {
        return (a, b) -> a % b;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        } else {
            ModuloOperand that = (ModuloOperand) obj;
            return Objects.equals(that.leftOperand, leftOperand)
                && Objects.equals(that.rightOperand, rightOperand);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftOperand, RuleSQLParser.MOD, rightOperand);
    }

    @Override
    public String toString() {
        return leftOperand.toString() + " % " + rightOperand.toString();
    }
}
