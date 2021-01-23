/*
 *
 *
 *
 *
 *
 */
package com.river.engine.formats.types.utils;

import com.river.engine.formats.exception.NullValueCalculationException;
import com.river.engine.formats.exception.ValueTypeNotSupportedException;
import com.river.engine.formats.types.Value;
import com.river.engine.formats.types.values.LongValue;
import com.river.engine.formats.types.values.NullValue;
import com.river.engine.formats.types.values.NumberValue;
import com.river.engine.formats.types.values.StringValue;
import lombok.experimental.UtilityClass;

import java.util.Date;

/**
 * A helper class for creating the {@link Value} instance from {@link Object}
 *
 */
@UtilityClass
public class ValueConverter {

    public static Value convert(Object value) {
        if (value == null) {
            return ValueConstants.NULL_VALUE;
        } else if (value instanceof String) {
            return new StringValue((String) value);
        } else if (value instanceof Byte) {
            return new NumberValue((Byte) value);
        } else if (value instanceof Short) {
            return new NumberValue((Short) value);
        } else if (value instanceof Integer) {
            return new NumberValue((Integer) value);
        } else if (value instanceof Long) {
            return new NumberValue((Long) value);
        } else if (value instanceof Float) {
            return new NumberValue((Float) value);
        } else if (value instanceof Double) {
            return new NumberValue((Double) value);
        } else if (value instanceof Date) {
            return new NumberValue(((Date) value).getTime());
        } else if (value instanceof Boolean) {
            if ((Boolean) value) {
                return ValueConstants.TRUE_VALUE;
            } else {
                return ValueConstants.FALSE_VALUE;
            }
        } else {
            throw new ValueTypeNotSupportedException("No such value type {} supported", value.getClass());
        }
    }

    public static double numberValue(Value value) {
        if (value instanceof NumberValue) {
            return ((NumberValue) value).getNumber();
        } else if (value instanceof StringValue) {
            return ((StringValue) value).numberValue();
        } else if (value instanceof LongValue) {
            return ((LongValue) value).getDoubleNumber();
        } else if (value instanceof NullValue) {
            throw new NullValueCalculationException("Null value type couldn't be used in mathematical calculation.");
        } else {
            throw new ValueTypeNotSupportedException("The value type {} couldn't get the numeric value", value.getClass());
        }
    }
}
