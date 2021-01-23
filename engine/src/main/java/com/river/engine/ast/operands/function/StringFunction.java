package com.river.engine.ast.operands.function;

import com.river.engine.formats.types.Value;
import com.river.engine.ast.Operand;
import com.river.engine.ast.operands.base.AbstractAggregationOperand;
import com.river.engine.enmu.DataType;
import com.river.engine.context.MetricContext;
import com.river.engine.context.RuleContext;

/**
 * @author Juyony Chen
 * @version 1.0.0, 2021/1/22 18:29
 * @since 1.0.0, 2021/1/22 18:29
 **/
public class StringFunction extends AbstractAggregationOperand {


    public StringFunction(Operand operand){
        super(operand, DataType.STRING);
    }

    @Override
    protected String functionName() {
        return "String";
    }

    @Override
    public Value calculate(MetricContext metric, RuleContext context) {
        return null;
    }

    @Override
    public Value calculate(MetricContext context) {
        return null;
    }
}
