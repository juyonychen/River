/*
 *
 *
 *
 *
 *
 */
package com.river.engine.ast.operands.base;


import com.river.engine.context.MetricContext;
import com.river.engine.formats.types.Value;
import com.river.engine.formats.types.values.NumberValue;
import com.river.engine.ast.FunctionOperand;
import com.river.engine.ast.Operand;
import com.river.engine.enmu.DataType;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * AbstractAggregationOperand
 */
@Slf4j
public abstract class AbstractAggregationOperand extends AbstractFunctionOperand implements FunctionOperand {
    private static final long serialVersionUID = 922622920426006147L;

    protected AbstractAggregationOperand(Operand innerOperand, DataType dataType) {
        super(innerOperand, dataType);
    }





    /**
     * function name
     * @return
     */
    protected abstract String functionName();


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        } else {
            AbstractAggregationOperand that = (AbstractAggregationOperand) obj;

            return true;//todo
//            return Objects.equals(that.functionName(), functionName())
//                && Objects.equals(that.startMillis, startMillis)
//                && Objects.equals(that.endMillis, endMillis)
//                && Objects.equals(that.innerOperand, innerOperand)
//                && Objects.equals(that.predicate, predicate);
        }
    }


    protected synchronized Value calculate(MetricContext context, String function, NumberValue operandValue){

        //默认返回0
        return new NumberValue(0L);
    }


    @Override
    public int hashCode() {
        return Objects.hash(functionName(),  innerOperand, this.dataType);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        // Append function name
        builder.append(functionName());
        builder.append("(");

        // Append inner operand's value
        builder.append(innerOperand.toString());

        if (dataType != null) {
            builder.append(", ");
            builder.append(dataType);
        }

        builder.append(")");
        return builder.toString();
    }

}
