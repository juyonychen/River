/*
 *
 *
 *
 *
 *
 */
package com.river.engine.ast.operands.base;


import com.river.common.dto.enums.DataType;
import com.river.engine.ast.Operand;
import com.river.engine.ast.operands.FunctionOperand;

/**
 *
 */
public abstract class AbstractFunctionOperand implements Operand, FunctionOperand {
    private static final long serialVersionUID = 5137469905650561565L;

    protected final DataType dataType;
    protected final String parameter;

    public AbstractFunctionOperand(String parameter, DataType dataType) {
        this.parameter = parameter;
        this.dataType = dataType;
    }

    public abstract String functionName();


    @Override
    public DataType getDataType(){
        return this.dataType;
    }

    public String getParameter(){
        return this.parameter;
    }

}
