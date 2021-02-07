
package com.river.engine.ast.expressions.binary;


import com.river.engine.ast.Expression;
import com.river.engine.ast.expressions.base.AbstractBinaryExpression;
import com.river.engine.context.DataContext;
import com.river.engine.grammar.RuleSQLParser;
import lombok.NonNull;

import java.util.Objects;

/**
 * A and expression for two expression
 *
 */
public class AndExpression extends AbstractBinaryExpression {
    private static final long serialVersionUID = 3063637967716582543L;

    public AndExpression(@NonNull Expression leftExpression, @NonNull Expression rightExpression) {
        super(leftExpression, rightExpression);
    }

    @Override
    public boolean evaluate(DataContext context) {
        return leftExpression.evaluate(context)
            && rightExpression.evaluate(context);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        } else {
            return Objects.equals(((AndExpression) obj).leftExpression, leftExpression)
                && Objects.equals(((AndExpression) obj).rightExpression, rightExpression);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftExpression, RuleSQLParser.AND, rightExpression);
    }

    @Override
    public String toString() {
        return leftExpression.toString() + " AND " + rightExpression.toString();
    }
}
