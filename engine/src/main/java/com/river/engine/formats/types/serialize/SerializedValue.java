/*
 *
 *
 *
 *
 *
 */
package com.river.engine.formats.types.serialize;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

/**
 * This interface must be implemented by every class whose objects have to be serialized to their binary representation
 * and vice-versa. In particular, records have to implement this interface in order to specify how their data can be
 * transferred to a binary representation.
 *
 * <p>When implementing this Interface make sure that the implementing class has a default
 * (zero-argument) constructor!
 *
 */
public interface SerializedValue extends Serializable {

    /**
     * Writes the object's internal data to the given data output view.
     *
     * @param out the output view to receive the data.
     * @throws IOException thrown if any error occurs while writing to the output stream
     */
    void write(DataOutput out) throws IOException;

    /**
     * Reads the object's internal data from the given data input view.
     *
     * @param in the input view to read the data from
     * @throws IOException thrown if any error occurs while reading from the input stream
     */
    void read(DataInput in) throws IOException;

    /**
     * Read the string from the {@link DataInput} value storage.
     * The string was encoded in UTF-8.
     */
    default String readString(DataInput in) throws IOException {
        int strLength = in.readInt();

        byte[] strBytes = new byte[strLength];
        for (int i = 0; i < strLength; i++) {
            strBytes[i] = in.readByte();
        }

        return new String(strBytes, StandardCharsets.UTF_8);
    }

    /**
     * Write the string to byte buffer, a helper function.
     * The string was encoded in UTF-8.
     */
    default void writeString(DataOutput out, String strToWrite) throws IOException {
        byte[] writeBytes = strToWrite.getBytes(StandardCharsets.UTF_8);

        out.writeInt(writeBytes.length);
        for (byte byteToWrite : writeBytes) {
            out.writeByte(byteToWrite);
        }
    }
}
