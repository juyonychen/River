package com.river.common.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Juyony Chen
 * @version 1.0.0, 2021/1/22 18:38
 * @since 1.0.0, 2021/1/22 18:38
 **/
@Getter
@AllArgsConstructor
public enum DataType {

    STRING("1", "String"),

    INT("2", "Int"),

    LONG("3", "Long"),

    DATA_TIME("4", "DataTime")
    ;

    private String code;
    private String name;


    private static final Map<String, DataType> dataTypeMap = new HashMap<>();

    static {
        DataType[] values = DataType.values();
        for (DataType dataType : values) {
            dataTypeMap.put(dataType.code, dataType);
        }
    }

    public static DataType convert(@NonNull String code) {
        DataType dataType = dataTypeMap.get(code.toUpperCase());
        if (dataType != null) {
            return dataType;
        }
        throw new IllegalArgumentException("No such dataType [" + code + "] exist");
    }

}
