
package com.river.engine.ast.expressions.function;

import com.river.engine.ast.Expression;
import com.river.engine.ast.expressions.compare.EqualsExpression;
import com.river.engine.context.MetricContext;
import com.river.engine.grammar.RuleSQLParser;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.Objects;

/**
 * A not expression for negative a inner expression
 *
 */
@AllArgsConstructor
public class NotExpression implements Expression {
    private static final long serialVersionUID = -1218892322900474726L;

    @NonNull
    private final Expression innerExpression;

    @Override
    public boolean evaluate(MetricContext context) {
        return !innerExpression.evaluate(context);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        } else {
            NotExpression that = (NotExpression) obj;
            return Objects.equals(that.innerExpression, innerExpression);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(RuleSQLParser.NOT, innerExpression);
    }

    @Override
    public String toString() {
        if (innerExpression instanceof EqualsExpression) {
            return ((EqualsExpression) innerExpression).toString(false);
        } else {
            return "NOT " + innerExpression.toString();
        }
    }
}
