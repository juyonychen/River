package com.river.common.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Juyony Chen
 * @version 1.0.0, 2020/11/30 14:29
 * @since 1.0.0, 2020/11/30 14:29
 **/
@Getter
@AllArgsConstructor
public enum RuleOperation {

    /**
     * Update a existed rule's sql & parameters
     */
    UPDATE("修改", "UPDATE"),

    /**
     * Create a new rule
     */
    ADD("新增", "ADD"),

    /**
     * Delete a existed rule
     */
    DELETE("刪除", "DELETE"),
    ;

    private String name;
    private String code;

    private static final Map<String, RuleOperation> ruleOperationMap = new HashMap<>();

    static {
        RuleOperation[] values = RuleOperation.values();
        for (RuleOperation ruleOperation : values) {
            ruleOperationMap.put(ruleOperation.code, ruleOperation);
        }
    }

    public static RuleOperation convert(@NonNull String code) {
        RuleOperation ruleOperation = ruleOperationMap.get(code.toUpperCase());
        if (ruleOperation != null) {
            return ruleOperation;
        }
        throw new IllegalArgumentException("No such ruleOperation [" + code + "] exist");
    }

}
