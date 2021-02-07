/*
 *
 *
 *
 *
 *
 */
package com.river.engine.formats.types.values;


import com.river.engine.formats.types.Value;
import com.river.engine.formats.types.ValueTypes;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * A special boolean {@link Value} for engine calculation
 */
public class ArrayValue implements Value<ArrayValue, Value[]> {
    private static final long serialVersionUID = 6059701095919817611L;

    private Value[] values;

    public ArrayValue() {
    }

    public ArrayValue(String[] strValues){
        values = new Value[strValues.length];
        for(int x = 0;x <= strValues.length; x++){
            values[x] = new StringValue(strValues[x]);
        }
    }

    public int length(){
        if(null == values){
            return 0;
        }else{
            return values.length;
        }
    }

    public ArrayValue(Value[] values) {
        this.values = values;
    }

    @Override
    public void copyTo(ArrayValue target) {
        target.values = this.values;
    }

    @Override
    public ArrayValue copy() {
        return new ArrayValue(this.values);
    }

    @Override
    public Value[] getValue() {
        return values;
    }

    @Override
    public int compareTo(ArrayValue o) {
       return 0;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        return null != obj;
    }

    @Override
    public byte typeIdentity() {
        return ValueTypes.BOOLEAN.getIdentity();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Value value :values){
            sb.append(value.toString()).append('_');
        }
        return sb.toString();
    }

    @Override
    public void write(DataOutput out) throws IOException {
        for(Value value : values){
            if(value instanceof StringValue){
                writeString(out, ((StringValue) value).getValue());
            }else if(value instanceof NumberValue){
                out.writeDouble(((NumberValue) value).getNumber());
            }else if(value instanceof  LongValue){
                out.writeLong(((LongValue) value).getNumber());
            }
        }
    }

    @Override
    public void read(DataInput in) throws IOException {
    }
}
