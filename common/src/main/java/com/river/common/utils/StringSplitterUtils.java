
package com.river.common.utils;

import com.google.common.base.Splitter;
import com.river.common.log.SampleLogFactory;
import lombok.experimental.UtilityClass;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * In our programming life, we always need to split strings with delimiters.
 * {@link String#split(String)} is the most intuitive choice and it is sweet when we use regex based split,
 * e.g.{@code .split("\\s+")}, but should we always use it regardless of scenarios? I'm afraid not.
 * {@link String#split(String)} can be bad especially when performance matters.
 * e.g. when you build a high-performance server or running ETL jobs.
 * <p>
 * We can note that {@link String#split(String)} receives a {@code regex} argument,
 * but we may just need a {@code char} as the delimiter.
 * <p>
 * Inspired by <a href="http://haokanga.github.io/java/2016/05/23/java-string-fast-split.html">this article</a>.
 * And totally rewrite it.
 * <p>
 * <strong>Warning!!!</strong> This utils doesn't support regular expression
 *
 *
 * @version 2.0.0, 2018-10-16 01:07
 * @since 2.0.0, 2018-10-16 01:07
 */
@UtilityClass
public class StringSplitterUtils {

    protected static final Logger log = SampleLogFactory.getLogger(StringSplitterUtils.class);
    private static final String STR_ARRAY_TOO_LONG = "The split str array's length was exceed the given size.";
    private static final String STR_ARRAY_LENGTH_INVALID = "The split str array was not equal to the given size.";

    /**
     * Split a str with a char, also include the regex char.
     */
    public static String[] split(String string, char delimiter) {
        int off = 0;
        int next;

        List<String> list = new ArrayList<>();

        while ((next = string.indexOf(delimiter, off)) != -1) {
            list.add(string.substring(off, next));
            off = next + 1;
        }

        // If no match was found, return this
        return getStrArray(string, off, list);
    }

    /**
     * Split the str with a char and a predefined array size.
     */
    public static String[] split(String string, char delimiter, int length) {
        int off = 0;
        int next;
        int index = 0;
        String[] result = new String[length];

        try {
            while ((next = string.indexOf(delimiter, off)) != -1) {
                result[index] = string.substring(off, next);
                off = next + 1;
                index++;
            }

            // If no match was found, return this
            if (off == 0) {
                return new String[]{string};
            }

            if (off <= string.length()) {
                result[index] = string.substring(off);
                index++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            log.error("split error,data :" + string, e);
            throw new IllegalArgumentException(STR_ARRAY_TOO_LONG);
        }

        // Add extra validation
        if (index != result.length) {
            log.error("split error,data :" + string);
            throw new IllegalArgumentException(STR_ARRAY_LENGTH_INVALID);
        }

        return result;
    }

    public static String[] split(String string, String delimiter) {
        if (delimiter.length() == 1) {
            return split(string, delimiter.charAt(0));
        } else {
            int off = 0;
            int next;

            List<String> result = new ArrayList<>();

            while ((next = string.indexOf(delimiter, off)) != -1) {
                result.add(string.substring(off, next));
                off = next + delimiter.length();
            }

            // If no match was found, return this
            return getStrArray(string, off, result);
        }
    }

    public static String[] split(String string, String delimiter, int length) {
        if (delimiter.length() == 1) {
            return split(string, delimiter.charAt(0), length);
        }

        int off = 0;
        int next;
        int index = 0;
        String[] result = new String[length];

        try {
            while ((next = string.indexOf(delimiter, off)) != -1) {
                result[index] = string.substring(off, next);
                off = next + delimiter.length();
                index++;
            }

            if (off <= string.length()) {
                result[index] = string.substring(off);
                index++;
            }

            // If no match was found, return this
            if (off == 0) {
                return new String[]{string};
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            log.error("split error,data :" + string, e);
            throw new IllegalArgumentException(STR_ARRAY_TOO_LONG);
        }

        // Add extra validation
        if (index != result.length) {
            log.error("split error,data :" + string);
            throw new IllegalArgumentException(STR_ARRAY_LENGTH_INVALID);
        }

        return result;
    }

    public static String[] split(String string, char delimiter, char wrapChar) {
        List<String> result = new ArrayList<>();

        int startIndex = 0;
        int endIndex = startIndex;
        boolean isWrapperContent = false;
        for (; endIndex < string.length(); endIndex++) {
            if (string.charAt(endIndex) == wrapChar) {
                isWrapperContent = !isWrapperContent;
            }

            if (string.charAt(endIndex) == delimiter && !isWrapperContent) {
                result.add(string.substring(startIndex, endIndex));
                startIndex = endIndex + 1;
            }
        }

        result.add(string.substring(startIndex, endIndex));

        return result.toArray(new String[0]);
    }

    public static String[] split(String string, char delimiter, char wrapChar, int length) {
        String[] result = new String[length];

        int arrayIndex = 0;
        int startIndex = 0;
        int endIndex = startIndex;
        boolean isWrapperContent = false;
        for (; endIndex < string.length(); endIndex++) {
            if (string.charAt(endIndex) == wrapChar) {
                isWrapperContent = !isWrapperContent;
            }

            if (string.charAt(endIndex) == delimiter && !isWrapperContent) {
                result[arrayIndex] = string.substring(startIndex, endIndex);
                arrayIndex++;
                startIndex = endIndex + 1;
            }
        }

        if (arrayIndex == result.length) {
            log.error("split error,data :" + string);
            throw new IllegalArgumentException(STR_ARRAY_TOO_LONG);
        }

        result[arrayIndex] = string.substring(startIndex, endIndex);

        return result;
    }

    public static String[] split(String string, String delimiter, String wrapChar, int length) {
        if (delimiter.length() == 1 && wrapChar.length() == 1) {
            return split(string, delimiter.charAt(0), wrapChar.charAt(0), length);
        }
        if (delimiter.equals(wrapChar)) {
            return new String[]{string};
        } else {
            int off = 0;
            int start = 0;
            int delNext;
            int wrapNext;
            int index = 0;
            boolean isWrapperContent = false;
            String[] result = new String[length];

            try {
                while ((delNext = string.indexOf(delimiter, off)) != -1) {
                    wrapNext = string.indexOf(wrapChar, off);
                    if (wrapNext != -1) {
                        if (wrapNext > delNext) {
                            if (!isWrapperContent) {
                                result[index] = string.substring(start, delNext);
                                off = delNext + delimiter.length();
                                start = off;
                                index++;
                            } else {
                                off = delNext + delimiter.length();
                                isWrapperContent = true;
                            }
                        } else if (wrapNext < delNext) {
                            if (!isWrapperContent) {
                                off = delNext + delimiter.length();
                                isWrapperContent = true;
                            } else {
                                result[index] = string.substring(start, delNext);
                                off = delNext + delimiter.length();
                                start = off;
                                index++;
                                isWrapperContent = false;
                            }
                        }
                    } else {
                        result[index] = string.substring(start, delNext);
                        off = delNext + delimiter.length();
                        start = off;
                        index++;
                    }
                }
                if (!Objects.equals(string.substring(off), "") || string.endsWith(delimiter)) {
                    result[index] = string.substring(start);
                    index++;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                log.error("split error,data :" + string, e);
                throw new IllegalArgumentException(STR_ARRAY_TOO_LONG);
            }
            // Add extra validation
            if (index != result.length) {
                log.error("split error,data :" + string);
                throw new IllegalArgumentException(STR_ARRAY_LENGTH_INVALID);
            }
            return result;
        }
    }

    private static String[] getStrArray(String string, int off, List<String> result) {
        if (off == 0) {
            return new String[]{string};
        }

        if (off <= string.length()) {
            // Add remaining segment
            result.add(string.substring(off));
        }

        String[] list = new String[result.size()];

        return result.toArray(list);
    }

    public static String[] split(String string, String delimiter, List<Integer> fieldConfigs) {
        List<String> list = Splitter.on(delimiter).splitToList(string);
        String[] result = new String[fieldConfigs.size()];

        //注释的代码不支持取特定的字段，只能按照顺序取字段
//        fieldConfigs.forEach(index -> {
//            if (index >= list.size()){
//                result[index] = "";
//            }else {
//                result[index] = list.get(index);
//            }
//        });

        //支持配置文件中按照index字段进行获取split数据
        for(int i=0; i < fieldConfigs.size(); i++){
            int index = fieldConfigs.get(i);
            if (index >= list.size()){
                result[i] = "";
            }else {
                result[i] = list.get(index);
            }
        }

        return result;
    }
}
