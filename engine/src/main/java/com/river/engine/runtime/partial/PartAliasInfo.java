package com.river.engine.runtime.partial;

import com.river.engine.ast.Operand;
import com.river.engine.enmu.AliasType;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Juyony Chen
 * @version 1.0.0, 2021/1/22 17:36
 * @since 1.0.0, 2021/1/22 17:36
 **/
@Data
@AllArgsConstructor
public class PartAliasInfo extends AbstractPartInfo{
    private static final long serialVersionUID = 4646803424144939963L;

    /**
     * 1- Column 2-FunctionAlias
     */
    private AliasType aliasType;
    private String aliasName;
    private Operand aliasOperand;
}
