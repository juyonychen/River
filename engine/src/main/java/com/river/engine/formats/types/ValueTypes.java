/*
 *
 *
 *
 *
 *
 */
package com.river.engine.formats.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * A value type identity holder for unique management, you shouldn't modify any exist id, add is welcome.
 *
 */
@AllArgsConstructor
public enum ValueTypes {

    /***/
    BOOLEAN((byte) 0),
    /***/
    NULL((byte) 1),
    /***/
    NUMBER((byte) 2),
    /***/
    STRING((byte) 3),

    INT((byte) 4),

    LONG((byte) 5)

    ;
    /***/
    @Getter
    private final byte identity;
}
