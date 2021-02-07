
package com.river.common.exception;


/**
 * Could cast the field to the desired type class
 *
 *
 * @version 1.0.0, 2018-05-28 14:09
 * @since 1.0.0, 2018-05-28 14:09
 */
public class EnumNotFoundException extends EngineRuntimeException {
    private static final long serialVersionUID = -693821939988179383L;

    public EnumNotFoundException(String message, Object... params) {
        super(message, ErrorCode.UNEXPECTED_ENUM_EXCEPTION, params);
    }

    public EnumNotFoundException(String message, Throwable cause, Object... params) {
        super(message, cause, ErrorCode.UNEXPECTED_ENUM_EXCEPTION, params);
    }
}
