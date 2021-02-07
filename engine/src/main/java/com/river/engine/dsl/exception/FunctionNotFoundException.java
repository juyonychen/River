/*
 *
 *
 *
 *
 *
 */
package com.river.engine.dsl.exception;

import com.river.common.exception.ErrorCode;
import com.river.common.exception.EngineRuntimeException;

/**
 * This aggregation function was not supported
 *
 */
public class FunctionNotFoundException extends EngineRuntimeException {
    private static final long serialVersionUID = 8940914521537276010L;

    public FunctionNotFoundException(String message, String... params) {
        super(message, ErrorCode.FUNCTION_KEY_NOT_EXISTED_IN_CALCULATE, params);
    }
}
