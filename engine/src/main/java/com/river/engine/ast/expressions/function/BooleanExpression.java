
package com.river.engine.ast.expressions.function;

import com.river.engine.context.DataContext;
import com.river.engine.ast.Expression;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Objects;

/**
 * A expression which return the predefined boolean value
 *

 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BooleanExpression implements Expression {
    private static final long serialVersionUID = 5541055674285170071L;

    public static final BooleanExpression TRUE_EXPRESSION = new BooleanExpression(true);

    public static final BooleanExpression FALSE_EXPRESSION = new BooleanExpression(false);

    private final boolean result;


    @Override
    public boolean evaluate(DataContext context) {
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        } else {
            BooleanExpression that = (BooleanExpression) obj;
            return result == that.result;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(result);
    }
}
