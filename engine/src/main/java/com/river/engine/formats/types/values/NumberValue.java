
package com.river.engine.formats.types.values;

import com.google.common.math.DoubleMath;
import com.river.engine.formats.types.Value;
import com.river.engine.formats.types.ValueTypes;
import com.river.engine.formats.types.utils.ValueConstants;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * A number wrapper for reducing box/unbox, easy to serialization and type compiling
 */
public class NumberValue implements Value<NumberValue, Double> {
    private static final long serialVersionUID = -6918847194195789673L;

    private double value; // NOSONAR No need to box/unbox in engine calculation

    public NumberValue() {
        this(0.0);
    }

    public NumberValue(byte value) {
        this.value = value;
    }

    public NumberValue(short value) {
        this.value = value;
    }

    public NumberValue(int value) {
        this.value = value;
    }

    public NumberValue(long value) {
        this.value = value;
    }

    public NumberValue(double value) {
        this.value = value;
    }

    public NumberValue(float value) {
        this.value = value;
    }

    public NumberValue(Byte value) {
        this.value = value;
    }

    public NumberValue(Short value) {
        this.value = value;
    }

    public NumberValue(Integer value) {
        this.value = value;
    }

    public NumberValue(Long value) {
        this.value = value;
    }

    public NumberValue(Float value) {
        this.value = value;
    }

    public NumberValue(Double value) {
        this.value = value;
    }

    @Override
    public void copyTo(NumberValue target) {
        target.value = this.value;
    }

    @Override
    public NumberValue copy() {
        return new NumberValue(this.value);
    }

    @Override
    public Double getValue() {
        return value;
    }

    @SuppressWarnings("squid:S4144")
    // Expose the number for avoiding some modification on its sub class, no need to box this value.
    public double getNumber() {
        return value;
    }

    public void updateNumber(double value) {
        this.value =  value;
    }

    public boolean isInteger() {
        // This method in Guava is cool.
        return DoubleMath.isMathematicalInteger(value);
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(this.value);
        return 31 + (int) (temp ^ temp >>> 32);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof NumberValue) {
            final NumberValue other = (NumberValue) obj;
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
    public int compareTo(NumberValue o) {
        final double other = o.value;
        return Double.compare(this.value, other);
    }

    private static DecimalFormat df = new DecimalFormat("#.0000");

    @Override
    public String toString() {
       if(isNaN()){
           return "NaN";
       }else{
           return df.format(value);
       }
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeDouble(value);
    }

    @Override
    public void read(DataInput in) throws IOException {
        this.value = in.readDouble();
    }

    public boolean isNaN() {
        return Double.isNaN(value);
    }
}
