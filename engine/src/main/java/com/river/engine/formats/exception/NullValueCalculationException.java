/*
 *
 *
 *
 *
 *
 */
package com.river.engine.formats.exception;


import com.river.common.exception.ErrorCode;
import com.river.common.exception.EuphoriaRuntimeException;

/**
 * The null value couldn't be used in mathematical calculation
 *
 */
public class NullValueCalculationException extends EuphoriaRuntimeException {
    private static final long serialVersionUID = -7775035118632923291L;

    public NullValueCalculationException(String message, Object... messageParams) {
        super(message, ErrorCode.NULL_VALUE_CALCULATION, messageParams);
    }
}
