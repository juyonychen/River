package com.river.common.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Juyony Chen
 * @version 1.0.0, 2020/11/30 14:37
 * @since 1.0.0, 2020/11/30 14:37
 **/
@Getter
@AllArgsConstructor
public enum AlertSeverity {

    /**
     * Metric health status, used in rule definition.
     */
    // Bit priority 00000
    NORMAL("一般", "normal", 0),
    // Bit priority 00001
    WARNING("警告", "warning", 1),
    // Bit priority 00010
    CRITICAL("严重", "critical", 2),
    // Bit priority 00100
    FATAL("灾难", "fatal", 3),
    ;
    // Bit priority 01000, 10000 in the future

    private String name;
    private String code;
    private Integer priority;

    private static final Map<String, AlertSeverity> alertSeverityMap = new HashMap<>();

    static {
        AlertSeverity[] values = AlertSeverity.values();
        for (AlertSeverity alertSeverity : values) {
            alertSeverityMap.put(alertSeverity.code, alertSeverity);
        }
    }

    public static AlertSeverity convert(@NonNull String code) {
        AlertSeverity alertSeverity = alertSeverityMap.get(code);
        if (alertSeverity != null) {
            return alertSeverity;
        }
        throw new IllegalArgumentException("No such alertSeverity [" + code + "] exist");
    }

}