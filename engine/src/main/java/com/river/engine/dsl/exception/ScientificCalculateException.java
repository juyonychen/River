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
 * A exception of scientific calculation
 *
 */
public class ScientificCalculateException extends EuphoriaRuntimeException {
    private static final long serialVersionUID = -7595914420885890844L;

    public ScientificCalculateException(String message, Object... messageParams) {
        super(message, ErrorCode.NUMERIC_NOT_VALID, messageParams);
    }
}
