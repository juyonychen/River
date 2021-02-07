
package com.river.common.exception;


/**
 * Could cast the field to the desired type class
 *
 *
 * @version 1.0.0, 2018-05-28 14:09
 * @since 1.0.0, 2018-05-28 14:09
 */
public class TypeCastException extends EngineRuntimeException {
    private static final long serialVersionUID = -693821939988179383L;

    public TypeCastException(String message, Object... params) {
        super(message, ErrorCode.FAILURE_IN_FIELD_CAST, params);
    }

    public TypeCastException(String message, Throwable cause, Object... params) {
        super(message, cause, ErrorCode.FAILURE_IN_FIELD_CAST, params);
    }
}
