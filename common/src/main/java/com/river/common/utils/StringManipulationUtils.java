
package com.river.common.utils;

import com.google.common.base.Ascii;
import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.function.Predicate;

import static com.google.common.base.CaseFormat.*;
import static com.google.common.base.CharMatcher.inRange;
import static com.google.common.base.CharMatcher.is;

/**
 * A String case format conversion utils inspired by IDEA plugin StringManipulation. Base on Guava's {@link CharMatcher}
 *
 *
 * @version 1.0.0, 2018-05-25 15:17
 * @since 1.0.0, 2018-05-25 15:17
 */
@UtilityClass
public class StringManipulationUtils {

    private static final CharMatcher INNER_LOWER_HYPHEN = is('-').or(inRange('a', 'z')).precomputed();

    private static final CharMatcher INNER_LOWER_UNDERSCORE = is('_').or(inRange('a', 'z')).precomputed();

    private static final CharMatcher INNER_UPPER_UNDERSCORE = is('_').or(inRange('A', 'Z')).precomputed();

    private static final CharMatcher INNER_CASE_CAMEL = inRange('a', 'z').or(inRange('A', 'Z')).precomputed();

    public static String convertToLowerHyphen(String str) {
        return convert(getStrFormat(str), LOWER_HYPHEN, str);
    }

    public static String convertToLowerUnderscore(String str) {
        return convert(getStrFormat(str), LOWER_UNDERSCORE, str);
    }

    public static String convertToLowerCamel(String str) {
        return convert(getStrFormat(str), LOWER_CAMEL, str);
    }

    public static String convertToUpperCamel(String str) {
        return convert(getStrFormat(str), UPPER_CAMEL, str);
    }

    public static String convertToUpperUnderscore(String str) {
        return convert(getStrFormat(str), UPPER_UNDERSCORE, str);
    }

    private static String convert(CaseFormat fromFormat, CaseFormat toFormat, String str) {
        return fromFormat == toFormat ? str : fromFormat.converterTo(toFormat).convert(str);
    }

    private static CaseFormat getStrFormat(String str) {
        return Arrays.stream(CharSequenceFormat.values())
            .filter(format -> format.matches(str))
            .findFirst()
            .map(CharSequenceFormat::getFormat)
            .orElseThrow(() -> new IllegalArgumentException("The str [" + str + "] isn't a valid format"));
    }

    private enum CharSequenceFormat {

        UPPER_UNDERSCORE(INNER_UPPER_UNDERSCORE::matchesAllOf, CaseFormat.UPPER_UNDERSCORE),
        LOWER_HYPHEN(INNER_LOWER_HYPHEN::matchesAllOf, CaseFormat.LOWER_HYPHEN),
        LOWER_UNDERSCORE(INNER_LOWER_UNDERSCORE::matchesAllOf, CaseFormat.LOWER_UNDERSCORE),
        LOWER_CAMEL(s -> INNER_CASE_CAMEL.matchesAllOf(s) && Ascii.isLowerCase(s.charAt(0)), CaseFormat.LOWER_CAMEL),
        UPPER_CAMEL(s -> INNER_CASE_CAMEL.matchesAllOf(s) && Ascii.isUpperCase(s.charAt(0)), CaseFormat.UPPER_CAMEL);

        private Predicate<String> matcher;

        private CaseFormat format;

        CharSequenceFormat(Predicate<String> matcher, CaseFormat format) {
            this.matcher = matcher;
            this.format = format;
        }

        public CaseFormat getFormat() {
            return format;
        }

        public boolean matches(String str) {
            return matcher.test(str);
        }
    }
}
