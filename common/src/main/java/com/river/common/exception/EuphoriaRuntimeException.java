
package com.river.common.exception;

import lombok.Getter;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

import java.util.UUID;

/**
 * The business exception abstraction for euphoria
 *

 */
public class EuphoriaRuntimeException extends RuntimeException {
    private static final long serialVersionUID = -6291826441882799347L;

    /**
     * Add a UUID as traceId to better debug from the logs
     */
    @Getter
    private final String traceId = UUID.randomUUID().toString();

    /**
     * A common business specified error code for sorting the exception
     */
    @Getter
    private final ErrorCode errorCode;

    private final String detailedMessage;

    protected EuphoriaRuntimeException(String message, ErrorCode errorCode, Object... messageParams) {
        this(message, null, errorCode, messageParams);
    }

    protected EuphoriaRuntimeException(Throwable cause, ErrorCode errorCode) {
        this(null, cause, errorCode);
    }

    /**
     * This altered constructor could have some extra function for simplify the exception message generation.
     * Message could have extra parameter like sl4j log.
     *
     * @param message       Exception message, {@code {}} could be replaced by messageParams, using {@link Object#toString}
     * @param cause         The source of the exception
     * @param errorCode     The custom error code for group the alert business exception
     * @param messageParams The extra exception message parameter
     */
    protected EuphoriaRuntimeException(String message, Throwable cause, ErrorCode errorCode, Object... messageParams) {
        super(cause);
        if (errorCode == null) {
            throw new NullPointerException("Error Code shouldn't be null, it contains the main category of exception");
        }
        this.errorCode = errorCode;
        this.detailedMessage = createMessage(message, messageParams);
    }

    private String createMessage(String message, Object... messageParams) {
        if (message != null) {
            if (messageParams != null && messageParams.length > 0) {
                // Just use slf4j-api for ease
                FormattingTuple ft = MessageFormatter.arrayFormat(message, messageParams);
                return ft.getMessage();
            } else {
                return message;
            }
        }
        return "Exception message was not provided";
    }

    /**
     * Better exception message handle, easy to log or user friendly display
     */
    @Override
    public String getMessage() {
        return String.format("[%d]%s, TraceId: %s, Message: %s", errorCode.getCode(), errorCode.getDesc(), traceId, detailedMessage);
    }
}
