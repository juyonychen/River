
package com.river.engine.formats.exception;


import com.river.common.exception.ErrorCode;
import com.river.common.exception.EuphoriaRuntimeException;

public class ValueTypeNotSupportedException extends EuphoriaRuntimeException {
    private static final long serialVersionUID = -981298407051120873L;

    /**
     *
     * @param message
     * @param params
     */
    public ValueTypeNotSupportedException(String message, Object... params) {
        super(message, ErrorCode.NO_SUPPORTED_VALUE_TYPE, params);
    }
}
