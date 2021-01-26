
package com.river.engine.formats.types.values;

import com.google.common.math.DoubleMath;
import com.river.engine.formats.types.Value;
import com.river.engine.formats.types.ValueTypes;
import com.river.engine.formats.types.utils.ValueConstants;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * A number wrapper for reducing box/unbox, easy to serialization and type compiling
 */
public class LongValue implements Value<LongValue, Long> {
    private static final long serialVersionUID = -6918847194195789673L;

    private long value; // NOSONAR No need to box/unbox in engine calculation

    public LongValue() {
        this(0);
    }

    public LongValue(byte value) {
        this.value = value;
    }

    public LongValue(short value) {
        this.value = value;
    }

    public LongValue(long value) {
        this.value = value;
    }

    public LongValue(int value) {
        this.value = value;
    }

    public LongValue(Byte value) {
        this.value = value;
    }

    public LongValue(Short value) {
        this.value = value;
    }

    public LongValue(Integer value) {
        this.value = value;
    }

    @Override
    public void copyTo(LongValue target) {
        target.value = this.value;
    }

    @Override
    public LongValue copy() {
        return new LongValue(this.value);
    }

    @Override
    public Long getValue() {
        return value;
    }


    @SuppressWarnings("squid:S4144")
    // Expose the number for avoiding some modification on its sub class, no need to box this value.
    public long getNumber() {
        return value;
    }

    public double getDoubleNumber(){
        return value;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(this.value);
        return 31 + (int) (temp ^ temp >>> 32);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LongValue) {
            final LongValue other = (LongValue) obj;
            return DoubleMath.fuzzyEquals(value, other.value, ValueConstants.OPERAND_CALCULATE_PRECISE);
        } else if (obj instanceof StringValue) {
            // StringValue could be the number value str. This should be added.
            double numberValue = ((StringValue) obj).numberValue();
            if (!Double.isNaN(numberValue)) {
                return DoubleMath.fuzzyEquals(value, numberValue, ValueConstants.OPERAND_CALCULATE_PRECISE);
            }
        }
        return false;
    }

    @Override
    public byte typeIdentity() {
        return ValueTypes.NUMBER.getIdentity();
    }

    @Override
    public int compareTo(LongValue o) {
        final double other = o.value;
        return Double.compare(this.value, other);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeDouble(value);
    }

    @Override
    public void read(DataInput in) throws IOException {
        this.value = in.readInt();
    }

    public boolean isNaN() {
        return Double.isNaN(value);
    }
}
