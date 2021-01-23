/*
 *
 *
 *
 *
 *
 */
package com.river.engine.dsl.exception;

/**
 * The sql syntax was not legal
 *
 */
@SuppressWarnings("squid:MaximumInheritanceDepth")
public class SyntaxErrorException extends RuleSqlParseException {
    private static final long serialVersionUID = -6739848150530002953L;

    public SyntaxErrorException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
