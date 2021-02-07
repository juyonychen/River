package com.river.engine.ast.operands.function;

import com.river.common.dto.enums.DataType;
import com.river.engine.ast.operands.base.AbstractFunctionOperand;
import com.river.engine.context.DataContext;
import com.river.engine.formats.types.Value;
import com.river.engine.formats.types.values.StringValue;

import java.util.Map;

/**
 * @author Juyony Chen
 * @version 1.0.0, 2021/1/22 18:30
 * @since 1.0.0, 2021/1/22 18:30
 **/
public class IntFunction extends AbstractFunctionOperand {

    public IntFunction(String parameter){
        super(parameter, DataType.INT);
    }

    @Override
    public String functionName() {
        return DataType.INT.name();
    }

    @Override
    public Value calculate(DataContext metric, Map<String,String> context) {
        return calculate(metric);
    }

    @Override
    public Value calculate(DataContext context) {
        return new StringValue(context.getStringValue(parameter));
    }

}
