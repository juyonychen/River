/*
 *
 *
 *
 *
 *
 */
package com.river.engine.ast.operands.base;


import com.river.engine.ast.Operand;
import com.river.engine.enmu.DataType;
import lombok.Getter;

/**
 * abstract unary operators, a union function operand for nest function and aggregation function
 *
 */
@Getter
public abstract class AbstractFunctionOperand implements Operand {
    private static final long serialVersionUID = 5137469905650561565L;

    protected final Operand innerOperand;

    protected final DataType dataType;

    protected AbstractFunctionOperand(Operand innerOperand, DataType dataType) {
        this.innerOperand = innerOperand;
        this.dataType = dataType;
    }

}
