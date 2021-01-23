package com.river.engine.formats.types.values;

import com.river.engine.formats.types.Value;
import com.river.engine.formats.types.ValueTypes;
import lombok.extern.slf4j.Slf4j;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

/**
 * A special value for string, would convert to char array for better serialization.
 */
@Slf4j
public class StringValue implements Value<StringValue, String> {
    private static final long serialVersionUID = 4671646219755693618L;

    private static final String EMPTY_STRING = "";

    private String value;

    private Double doubleValue;

    private int hashCode;

    public StringValue() {
        this.value = EMPTY_STRING;
    }

    public StringValue(String value) {
        this.value = value;
    }

    public StringValue(StringValue sv) {
        this.value = sv.value;
        this.hashCode = sv.hashCode;
        this.doubleValue = sv.doubleValue;
    }

    public double numberValue() {
        if (doubleValue == null) {
            // Init it.
            this.doubleValue = stringToDouble(value);
        }
        return doubleValue;
    }

    @Override
    public void copyTo(StringValue target) {
        target.hashCode = this.hashCode;
        target.value = this.value;
    }

    @Override
    public StringValue copy() {
        return new StringValue(this);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public int compareTo(StringValue o) {
        return value.compareTo(o.value);
    }

    @Override
    public int hashCode() {
        int h = this.hashCode;
        int length = value.length();
        if (h == 0 && length > 0) {
            this.hashCode = value.hashCode();
        }
        return h;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof StringValue) {
            final StringValue other = (StringValue) obj;
            return Objects.equals(value, other.value);
        } else if (obj instanceof NumberValue) {
            // StringValue could be the number value str. This should be added.
            double numberValue = this.numberValue();
            if (!Double.isNaN(numberValue)) {
                return Double.doubleToLongBits(((NumberValue) obj).getNumber()) == Double.doubleToLongBits(numberValue);
            }
        } else if (obj instanceof BooleanValue) {
            // Boolean could also be compared
            return Objects.equals(value, obj.toString());
        }
        return false;
    }

    @Override
    public byte typeIdentity() {
        return ValueTypes.STRING.getIdentity();
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        writeString(out, value);
    }

    @Override
    public void read(DataInput in) throws IOException {
        this.value = readString(in);
        this.doubleValue = stringToDouble(value);
    }

    private Double stringToDouble(String str) {
        try {
            // Try to parse the str into number if some mathematical calculation happened on this field
            return Double.parseDouble(str);
        } catch (Exception e) {
            log.error("StringToDouble error:", e);
            return Double.NaN;
        }
    }
}
