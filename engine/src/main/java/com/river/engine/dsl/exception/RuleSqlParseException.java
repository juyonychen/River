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
 * The common exception for rule sql parsing
 *
 */
public class RuleSqlParseException extends EuphoriaRuntimeException {
    private static final long serialVersionUID = -6368775989954662617L;

    public RuleSqlParseException(String message) {
        super(message, ErrorCode.SQL_FORMAT_NOT_VALID);
    }

    public RuleSqlParseException(String message, Throwable cause) {
        super(message, cause, ErrorCode.SQL_FORMAT_NOT_VALID);
    }
}
