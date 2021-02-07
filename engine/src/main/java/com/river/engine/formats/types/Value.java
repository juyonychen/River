/*
 * Copyright © 2013-2018 Suning.com Co., Ltd.
 *
 * This file is part of euphoria project.
 * It can not be copied and/or distributed without the express
 * permission of cloudytrace group.
 */
package com.river.engine.formats.types;

import com.river.engine.formats.types.serialize.SerializedValue;

import java.io.Serializable;

/**
 * The parent type for euphoria value type, original from flink {@code CopyableValue}.
 */
public interface Value<T extends Value, V extends Serializable> extends SerializedValue, Comparable<T> {

    /**
     * Performs a deep copy of this object into the {@code target} instance.
     *
     * @param target Object to copy into.
     */
    void copyTo(T target);

    /**
     * Performs a deep copy of this object into a new instance.
     * <p>
     * This method is useful for generic user-defined functions to clone a
     * {@link Value} when storing multiple objects. With object reuse
     * a deep copy must be created and type erasure prevents calling new.
     *
     * @return New object with copied fields.
     */
    T copy();

    /**
     * Returns the value of the encapsulated primitive type.
     */
    V getValue();

    /**
     * All values must override the hash-code function to generate proper deterministic hash codes,
     * based on their contents.
     *
     * @return The hash code of the value
     */
    @Override
    int hashCode();

    /**
     * Compares the object on equality with another object.
     *
     * @param other The other object to compare against.
     * @return True, if this object is identical to the other object, false otherwise.
     */
    @Override
    boolean equals(Object other);

    /**
     * The type identity for unique {@link Value} type
     */
    byte typeIdentity();
}
