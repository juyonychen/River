/*
 *
 *
 *
 *
 *
 */
package com.river.engine.dsl.handler;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.*;

/**
 * A error handle strategy for antlr
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ThrowingExceptionErrorStrategy extends DefaultErrorStrategy {

    private static final ThrowingExceptionErrorStrategy INSTANCE = new ThrowingExceptionErrorStrategy();

    /**
     * Instead of recovering from exception e, rethrow it wrapped in a generic RuntimeException so
     * it is not caught by the rule function catches. Exception e is the "cause" of the
     * RuntimeException.
     */
    @Override
    public void recover(Parser recognizer, RecognitionException e) {
        throw e;
    }

    /**
     * Make sure we don't attempt to recover inline; if the parser successfully recovers, it won't
     * throw an exception.
     */
    @Override
    public Token recoverInline(Parser recognizer) {
        throw new InputMismatchException(recognizer);
    }

    /**
     * Make sure we don't attempt to recover from problems in sub-rules.
     */
    @Override
    public void sync(Parser recognizer) {
        // No need to use
    }

    public static ThrowingExceptionErrorStrategy getInstance() {
        return INSTANCE;
    }
}
