package com.river.engine.ast.operands.function;

import com.river.common.dto.enums.DataType;
import com.river.engine.ast.operands.base.AbstractFunctionOperand;
import com.river.engine.context.DataContext;
import com.river.engine.formats.types.Value;
import com.river.engine.formats.types.values.StringValue;

import java.util.Map;

/**
 * @author Juyony Chen
 * @version 1.0.0, 2021/1/26 14:29
 * @since 1.0.0, 2021/1/26 14:29
 **/
public class LongFunction extends AbstractFunctionOperand {


    public LongFunction(String parameter){
        super(parameter, DataType.LONG);
    }

    @Override
    public String functionName() {
        return DataType.LONG.name();
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
