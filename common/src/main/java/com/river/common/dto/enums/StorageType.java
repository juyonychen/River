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
public enum StorageType {

    KAFKA("Kafka", "KAFKA"),


    CH("ClickHouse", "CH"),


    FILE("File", "FILE"),


    JSON("JSON", "JSON")
    ;

    private String name;
    private String code;

    private static final Map<String, StorageType> storageTypeMap = new HashMap<>();

    static {
        StorageType[] values = StorageType.values();
        for (StorageType storageType : values) {
            storageTypeMap.put(storageType.code, storageType);
        }
    }

    public static StorageType convert(@NonNull String code) {
        StorageType storageType = storageTypeMap.get(code.toUpperCase());
        if (storageType != null) {
            return storageType;
        }
        throw new IllegalArgumentException("No such storageType [" + code + "] exist");
    }

}
