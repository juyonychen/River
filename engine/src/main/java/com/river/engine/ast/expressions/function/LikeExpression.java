
package com.river.engine.ast.expressions.function;


import com.river.engine.ast.Expression;
import com.river.engine.ast.Operand;
import com.river.engine.ast.operands.operand.StringOperand;
import com.river.engine.context.DataContext;
import com.river.engine.grammar.RuleSQLParser;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.Objects;

/**
 * A in expression for judging whether a operand contains in a collection of operands.
 *
 */
@AllArgsConstructor
public class LikeExpression implements Expression {
    private static final long serialVersionUID = 4916903919906487382L;

    @NonNull
    private final Operand comparator;

    @NonNull
    private final StringOperand likeMessage;

    @Override
    public boolean evaluate(DataContext context) {
        String currentValue = comparator.calculate(context).getValue().toString();
        return currentValue.matches(likeMessage.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        } else {
            LikeExpression that = (LikeExpression) obj;
            if (Objects.equals(that.comparator, comparator)) {
                if (Objects.equals(that.likeMessage, likeMessage)) {
                    return true;
                }
                return false;
            } else {
                return false;
            }
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(comparator, RuleSQLParser.IN, likeMessage);
    }

    @Override
    public String toString() {
        return comparator.toString() + " LIKE " + likeMessage.toString();
    }
}
