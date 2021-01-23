
package com.river.engine.ast.expressions.base;


import com.river.engine.ast.Expression;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Join two expressions into one expression.
 *
 *
 * @version 1.0.0, 2018-06-01 05:16
 * @since 1.0.0, 2018-06-01 05:16
 */
@Getter
@AllArgsConstructor
public abstract class AbstractBinaryExpression implements Expression {
    private static final long serialVersionUID = 5575802994693198303L;

    protected final Expression leftExpression;

    protected final Expression rightExpression;
}
