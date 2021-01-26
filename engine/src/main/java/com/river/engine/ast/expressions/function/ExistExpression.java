
package com.river.engine.ast.expressions.function;

import com.river.engine.ast.Expression;
import com.river.engine.ast.Operand;
import com.river.engine.context.DataContext;
import com.river.engine.formats.types.Value;
import com.river.engine.formats.types.values.NullValue;
import com.river.engine.grammar.RuleSQLParser;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.Objects;

/**
 * A exist expression for judging whether a JsonPath query metric exist in the upstream metrics
 */
@AllArgsConstructor
public class ExistExpression implements Expression {
    private static final long serialVersionUID = 5147587313728843702L;

    @NonNull
    private final Operand existOperand;

    @Override
    public boolean evaluate(DataContext context) {
        Value result = existOperand.calculate(context);
        return !(result instanceof NullValue);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        } else {
            ExistExpression that = (ExistExpression) obj;
            return Objects.equals(that.existOperand, existOperand);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(RuleSQLParser.EXISTS, existOperand);
    }

    @Override
    public String toString() {
        return "EXISTS " + existOperand.toString();
    }
}
