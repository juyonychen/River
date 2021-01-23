package com.river.engine.enmu;

import lombok.AllArgsConstructor;

/**
 * @author Juyony Chen
 * @version 1.0.0, 2021/1/22 18:38
 * @since 1.0.0, 2021/1/22 18:38
 **/
@AllArgsConstructor
public enum DataType {
    STRING(1, "String"),
    INT(2, "Int"),
    LONG(3, "Long");

    private int code;
    private String desc;
}
