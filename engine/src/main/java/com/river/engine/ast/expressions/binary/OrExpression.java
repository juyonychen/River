
package com.river.engine.ast.expressions.binary;


import com.river.engine.ast.Expression;
import com.river.engine.ast.expressions.base.AbstractBinaryExpression;
import com.river.engine.context.MetricContext;
import com.river.engine.grammar.RuleSQLParser;
import lombok.NonNull;

import java.util.Objects;

/**
 * A or expression for two expression
 *
 */
public class OrExpression extends AbstractBinaryExpression {
    private static final long serialVersionUID = 1646724795202441240L;

    public OrExpression(@NonNull Expression leftExpression, @NonNull Expression rightExpression) {
        super(leftExpression, rightExpression);
    }

    @Override
    public boolean evaluate(MetricContext context) {
        return leftExpression.evaluate(context)
            || rightExpression.evaluate(context);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        } else {
            return Objects.equals(((OrExpression) obj).leftExpression, leftExpression)
                && Objects.equals(((OrExpression) obj).rightExpression, rightExpression);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftExpression, RuleSQLParser.OR, rightExpression);
    }

    @Override
    public String toString() {
        return leftExpression.toString() + " OR " + rightExpression.toString();
    }
}
