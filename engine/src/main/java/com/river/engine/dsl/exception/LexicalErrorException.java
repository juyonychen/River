/*
 *
 *
 *
 *
 *
 */
package com.river.engine.dsl.exception;

/**
 * Some token validation error
 *
 */
@SuppressWarnings("squid:MaximumInheritanceDepth")
public class LexicalErrorException extends RuleSqlParseException {
    private static final long serialVersionUID = -419231283145881004L;

    public LexicalErrorException(String msg) {
        super(msg);
    }

    public LexicalErrorException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
