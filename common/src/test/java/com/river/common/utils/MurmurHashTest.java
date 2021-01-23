/*
 *
 *
 *
 *
 *
 */
package com.river.common.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 */
@SuppressWarnings({"squid:S1192", "squid:S00100"})
class MurmurHashTest {

    @Test
    void testHashByteArrayOverload() {
        String input = "hashthis";
        byte[] inputBytes = input.getBytes();

        int hashOfString = MurmurHash.hash(input);
        assertEquals(
            hashOfString,
            MurmurHash.hash(inputBytes),
            "MurmurHash.hash(byte[]) did not match MurmurHash.hash(String)"
        );

        Object bytesAsObject = inputBytes;
        assertEquals(
            hashOfString,
            MurmurHash.hash(bytesAsObject),
            "MurmurHash.hash(Object) given a byte[] did not match MurmurHash.hash(String)"
        );
    }

    @Test
    void testHash() throws Exception {
        final long actualHash = MurmurHash.hash("hashthis");
        final long expectedHash = -1974946086L;

        assertEquals(
            expectedHash,
            actualHash,
            "MurmurHash.hash(String) returns wrong hash value"
        );
    }
}
