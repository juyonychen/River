package com.river.common.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Juyony Chen
 * @version 1.0.0, 2021/2/2 14:42
 * @since 1.0.0, 2021/2/2 14:42
 **/
@Getter
@AllArgsConstructor
public enum FromType {

    JSON("Json", "JSON"),


    SPLIT("split", "SPLIT"),


    JSONLIST("JsonList", "JSONLIST"),


    SPLITLIST("SplitList", "SPLITLIST")
    ;

    private String name;
    private String code;

    private static final Map<String, FromType> fromTypeMap = new HashMap<>();

    static {
        FromType[] values = FromType.values();
        for (FromType fromType : values) {
            fromTypeMap.put(fromType.code, fromType);
        }
    }

    public static FromType convert(@NonNull String code) {
        FromType fromType = fromTypeMap.get(code.toUpperCase());
        if (fromType != null) {
            return fromType;
        }
        throw new IllegalArgumentException("No such fromType [" + code + "] exist");
    }

}
