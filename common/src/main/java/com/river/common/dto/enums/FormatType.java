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
public enum FormatType {

    JSON("Json", "JSON"),


    SPLIT("split", "SPLIT")

    ;

    private String name;
    private String code;

    private static final Map<String, FormatType> formatTypeMap = new HashMap<>();

    static {
        FormatType[] values = FormatType.values();
        for (FormatType formatType : values) {
            formatTypeMap.put(formatType.code, formatType);
        }
    }

    public static FormatType convert(@NonNull String code) {
        FormatType formatType = formatTypeMap.get(code.toUpperCase());
        if (formatType != null) {
            return formatType;
        }
        throw new IllegalArgumentException("No such formatType [" + code + "] exist");
    }

}
