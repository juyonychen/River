
package com.river.engine.formats.types.values;

import com.river.engine.formats.types.Value;
import com.river.engine.formats.types.ValueTypes;
import com.river.engine.formats.types.utils.ValueConstants;
import lombok.NoArgsConstructor;

import java.io.DataInput;
import java.io.DataOutput;

/**
 * Null value indicates that this {@link Value} was null and couldn't be used in engine procession.
 * This value type would be used in euphoria SQL {@code NOT EXIST} calculation.
 */
@NoArgsConstructor
public class NullValue implements Value<NullValue, String> {
    private static final long serialVersionUID = -779679307936298352L;

    @Override
    public void copyTo(NullValue target) {
        // No need implement this method
    }

    @Override
    public NullValue copy() {
        return ValueConstants.NULL_VALUE;
    }

    @Override
    public String getValue() {
        return "null";
    }

    @Override
    public int compareTo(NullValue o) {
        return 0;
    }

    @Override
    public int hashCode() {
        return 53;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj != null && obj.getClass() == NullValue.class);
    }

    @Override
    public byte typeIdentity() {
        return ValueTypes.NULL.getIdentity();
    }

    @Override
    public String toString() {
        return "null";
    }

    @Override
    public void write(DataOutput out) {
        // Nothing to do for null value
    }

    @Override
    public void read(DataInput in) {
        // Nothing to do for null value
    }
}
