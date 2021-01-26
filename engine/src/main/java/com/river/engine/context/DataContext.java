/*
 *
 *
 *
 *
 *
 */
package com.river.engine.context;


import com.river.engine.formats.types.values.LongValue;
import com.river.engine.formats.types.values.NumberValue;
import com.river.engine.formats.types.values.StringValue;

public abstract class DataContext<T> {

    public abstract StringValue getStringValue(T parameter);

    public abstract LongValue getLongValue(T  parameter);

    public abstract NumberValue getNumberValue(T parameter);

}
