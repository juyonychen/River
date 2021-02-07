/*
 * Copyright © 2013-2018 Suning.com Co., Ltd.
 *
 * This file is part of euphoria project.
 * It can not be copied and/or distributed without the express
 * permission of cloudytrace group.
 */
package com.river.engine.formats.types.values;


import com.river.common.exception.TypeCastException;
import com.river.common.utils.TypeUtils;
import com.river.engine.formats.types.Value;
import com.river.engine.formats.types.ValueTypes;
import com.river.engine.formats.types.utils.ValueConstants;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * A special boolean {@link Value} for engine calculation
 */
public class BooleanValue implements Value<BooleanValue, Boolean> {
    private static final long serialVersionUID = 6059701095919817611L;

    private boolean value;

    public BooleanValue() {
        // No need to initialize
    }

    public BooleanValue(boolean value) {
        this.value = value;
    }

    @Override
    public void copyTo(BooleanValue target) {
        target.value = this.value;
    }

    @Override
    public BooleanValue copy() {
        return new BooleanValue(this.value);
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public int compareTo(BooleanValue o) {
        final int ov = o.value ? 1 : 0;
        final int tv = this.value ? 1 : 0;
        return tv - ov;
    }

    @Override
    public int hashCode() {
        return this.value ? 1 : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BooleanValue) {
            return ((BooleanValue) obj).value == this.value;
        } else if (obj instanceof StringValue || obj instanceof NumberValue) {
            try {
                Boolean booleanValue = TypeUtils.castToBoolean(((Value) obj).getValue());
                if (booleanValue != null) {
                    return booleanValue == value;
                }
            } catch (TypeCastException e) { // NOSONAR No need to print
                return false;
            }
        }
        return false;
    }

    @Override
    public byte typeIdentity() {
        return ValueTypes.BOOLEAN.getIdentity();
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static BooleanValue valueOf(boolean value) {
        return value ? ValueConstants.TRUE_VALUE : ValueConstants.FALSE_VALUE;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeBoolean(value);
    }

    @Override
    public void read(DataInput in) throws IOException {
        this.value = in.readBoolean();
    }
}
