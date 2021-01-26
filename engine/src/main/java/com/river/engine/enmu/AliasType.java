package com.river.engine.enmu;

import lombok.AllArgsConstructor;

/**
 * @author Juyony Chen
 * @version 1.0.0, 2021/1/22 17:53
 * @since 1.0.0, 2021/1/22 17:53
 **/
@AllArgsConstructor
public enum AliasType {

    COLUMN_ALIAS(1, "ColumnType"),

    NAMING_ALIAS(2, "NamingAlias"),

    FUNCTION_ALIAS(3, "FunctionAlias");

    private int code;
    private String desc;
}
