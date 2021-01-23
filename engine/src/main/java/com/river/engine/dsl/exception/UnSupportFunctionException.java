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
 * The sql parsing error in flink
 *
 */
public class UnSupportFunctionException extends EuphoriaRuntimeException {
    private static final long serialVersionUID = -1287476555109119065L;

    public UnSupportFunctionException(String msg, Object... params) {
        super(msg, ErrorCode.SQL_FORMAT_NOT_VALID, params);
    }
}
