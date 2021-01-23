
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
public class IntValue implements Value<IntValue, Integer> {
    private static final long serialVersionUID = -6918847194195789673L;

    private int value; // NOSONAR No need to box/unbox in engine calculation

    public IntValue() {
        this(0);
    }

    public IntValue(byte value) {
        this.value = value;
    }

    public IntValue(short value) {
        this.value = value;
    }

    public IntValue(int value) {
        this.value = value;
    }

    public IntValue(Byte value) {
        this.value = value;
    }

    public IntValue(Short value) {
        this.value = value;
    }

    public IntValue(Integer value) {
        this.value = value;
    }


    @Override
    public void copyTo(IntValue target) {
        target.value = this.value;
    }

    @Override
    public IntValue copy() {
        return new IntValue(this.value);
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @SuppressWarnings("squid:S4144")
    // Expose the number for avoiding some modification on its sub class, no need to box this value.
    public double getNumber() {
        return value;
    }

    public void updateNumber(Integer value) {
        this.value =  value;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(this.value);
        return 31 + (int) (temp ^ temp >>> 32);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IntValue) {
            final IntValue other = (IntValue) obj;
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
        return ValueTypes.INT.getIdentity();
    }

    @Override
    public int compareTo(IntValue o) {
        return Integer.compare(this.value, o.value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(value);
    }

    @Override
    public void read(DataInput in) throws IOException {
        this.value = in.readInt();
    }

}
