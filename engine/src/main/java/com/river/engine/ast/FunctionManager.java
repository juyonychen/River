
package com.river.engine.ast;

import com.google.common.collect.ImmutableMap;
import com.river.engine.ast.operands.base.AbstractFunctionOperand;
import com.river.engine.ast.operands.function.IntFunction;
import com.river.engine.ast.operands.function.LongFunction;
import com.river.engine.ast.operands.function.StringFunction;
import com.river.engine.dsl.exception.FunctionNotFoundException;
import com.river.engine.common.ReflectionSupport;
import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A operand function factory
 */
@UtilityClass
public class FunctionManager {

    private static final Map<String, Class<? extends AbstractFunctionOperand>> FUNCTION_NAMING = ImmutableMap
            .<String, Class<? extends AbstractFunctionOperand>>builder()
            .put("STRING", StringFunction.class)
            .put("INT", IntFunction.class)
            .put("LONG", LongFunction.class)
            .build();

    /**
     *
     * @param functionName
     * @param parameters
     * @return
     */
    public AbstractFunctionOperand createFunction(String functionName, Object... parameters) {
        final String functionKey = functionName.toUpperCase(Locale.CHINA);
        final Class<? extends AbstractFunctionOperand> functionClass = FUNCTION_NAMING.get(functionKey);

        if (functionClass == null) {
            throw new FunctionNotFoundException("No such function {} existed", functionKey);
        }
        checkNotNull(parameters, "Function parameters must not be null");

        return ReflectionSupport.newInstance(functionClass, convertParameterTypes(parameters), parameters);
    }

    /**
     *
     * @param parameters
     * @return
     */
    private Class<?>[] convertParameterTypes(Object... parameters) {
        return Arrays.stream(parameters)
                .map(obj -> {
                    if (obj instanceof Operand) {
                        return Operand.class;
                    } else if (obj instanceof Expression) {
                        return Expression.class;
                    }
                    return obj.getClass();
                })
                .toArray(Class[]::new);
    }


}
