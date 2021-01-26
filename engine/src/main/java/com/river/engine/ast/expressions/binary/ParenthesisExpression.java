
package com.river.engine.ast.expressions.binary;

import com.river.engine.ast.Expression;
import com.river.engine.ast.expressions.base.AbstractBinaryExpression;
import com.river.engine.context.DataContext;
import com.river.engine.grammar.RuleSQLParser;
import lombok.NonNull;

import java.util.Objects;

/**
 * The expression which wraps a inner expression
 *
 */
public class ParenthesisExpression extends AbstractBinaryExpression {
    private static final long serialVersionUID = -8167900572811244453L;

    public ParenthesisExpression(@NonNull Expression leftExpression) {
        super(leftExpression, null);
    }


    @Override
    public boolean evaluate(DataContext context) {
        return leftExpression.evaluate(context);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Expression)) {
            return false;
        } else {
            if (obj instanceof ParenthesisExpression) {
                return Objects.equals(((ParenthesisExpression) obj).leftExpression, leftExpression);
            } else {
                return Objects.equals(obj, leftExpression);
            }
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(RuleSQLParser.LBRACE, leftExpression);
    }

    @Override
    public String toString() {
        return "(" + leftExpression.toString() + ")";
    }
}
