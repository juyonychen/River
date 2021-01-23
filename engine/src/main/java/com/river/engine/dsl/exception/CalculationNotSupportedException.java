/*
 *
 *
 *
 *
 *
 */
package com.river.engine.dsl.exception;

import com.river.common.exception.ErrorCode;
import com.river.common.exception.EuphoriaRuntimeException;

/**
 * Some calculate operation was not supported or just be available in specified stage.
 *
 */
public class CalculationNotSupportedException extends EuphoriaRuntimeException {
    private static final long serialVersionUID = -4096161796800718137L;

    public CalculationNotSupportedException(String message, Object... messageParams) {
        super(message, ErrorCode.COMBINATOR_CALCULATE_ERROR, messageParams);
    }
}
