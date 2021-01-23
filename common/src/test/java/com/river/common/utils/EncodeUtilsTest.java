/*
 *
 *
 *
 *
 *
 */
package com.river.common.utils;

import com.google.common.base.Charsets;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

/**
 *
 */
@SuppressWarnings("squid:S1192")
class EncodeUtilsTest {

    @Test
    void hexEncode() {
        String input = "haha,i am a very long message";
        String result = EncodeUtils.encodeHex(input.getBytes());
        assertThat(new String(EncodeUtils.decodeHex(result), Charsets.UTF_8)).isEqualTo(input);

        byte[] bytes = new byte[]{1, 2, 15, 17};
        result = EncodeUtils.encodeHex(bytes);
        assertThat(result).isEqualTo("01020F11");

        input = "01020F11";
        assertThat(EncodeUtils.decodeHex(input)).hasSize(4).containsSequence((byte) 1, (byte) 2, (byte) 15, (byte) 17);

        try {
            input = "01020G11";
            EncodeUtils.decodeHex(input);
            fail("should throw exception before");
        } catch (Exception t) {
            assertThat(t).isInstanceOf(IllegalArgumentException.class);
        }

    }

    @Test
    @DisplayName("base64 encode")
    void base64Encode() {
        String input = "haha,i am a very long message";
        String result = EncodeUtils.encodeBase64(input.getBytes());
        assertThat(new String(EncodeUtils.decodeBase64(result), Charsets.UTF_8)).isEqualTo(input);

        byte[] bytes = new byte[]{5};
        result = EncodeUtils.encodeBase64(bytes);
        assertThat(result).isEqualTo("BQ==");

        bytes = new byte[]{1, 2, 15, 17, 127};
        result = EncodeUtils.encodeBase64(bytes);
        assertThat(result).isEqualTo("AQIPEX8=");
    }

    @Test
    @DisplayName("base64 url safe encode")
    void base64UrlSafeEncode() {
        String input = "haha,i am a very long message";
        String result = EncodeUtils.encodeBase64UrlSafe(input.getBytes());
        assertThat(new String(EncodeUtils.decodeBase64UrlSafe(result), Charsets.UTF_8)).isEqualTo(input);

        try {
            assertThat(result).isEqualTo(EncodeUtils.decodeBase64UrlSafe("AQIPE+8="));
            fail("should throw exception before");
        } catch (Exception t) {
            assertThat(t).isInstanceOf(IllegalArgumentException.class);
        }
    }
}
