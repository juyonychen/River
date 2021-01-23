
package com.river.common.utils;

import lombok.experimental.UtilityClass;

import java.security.SecureRandom;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A UUID generation utils contains some useful generation policy
 *
 *
 * @version 1.0.0, 2018-05-25 15:17
 * @since 1.0.0, 2018-05-25 15:17
 */
@UtilityClass
public class IdUtils {

    private static SecureRandom random = new SecureRandom();
    private static Snowflake snowflake = new Snowflake();

    public static String randomUUID() {
        return randomRawUUID().replaceAll("-", "");
    }

    public static String randomRawUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 使用ThreadLocalRandom随机生成Long.
     */
    public static long randomLong() {
        return ThreadLocalRandom.current().nextLong();
    }

    public static long snowflakeId() {
        return snowflake.nextId();
    }

    /**
     * 基于URLSafeBase64编码的SecureRandom随机生成bytes.
     */
    public static String randomBase64(int length) {
        byte[] randomBytes = new byte[length];
        random.nextBytes(randomBytes);
        return EncodeUtils.encodeBase64UrlSafe(randomBytes);
    }
}
