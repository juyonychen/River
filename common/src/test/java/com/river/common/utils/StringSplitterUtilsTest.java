/*
 *
 *
 *
 *
 *
 */
package com.river.common.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 *
 */
@SuppressWarnings({"squid:S1192", "squid:S00100"})
class StringSplitterUtilsTest {

    @Test
    void splitTheStrWithNoTrim() {
        String[] strings1 = StringSplitterUtils.split("a,b,c,d,e", ',');
        assertThat(strings1).containsExactly("a", "b", "c", "d", "e");

        String[] strings2 = StringSplitterUtils.split("a\tb\tc\td\te", '\t');
        assertThat(strings2).containsExactly("a", "b", "c", "d", "e");

        String[] strings3 = StringSplitterUtils.split("a|b|c|d|e", '|');
        assertThat(strings3).containsExactly("a", "b", "c", "d", "e");
    }

    @Test
    void splitTheStrWhichContainsTrim() {
        String[] strings = StringSplitterUtils.split("a,b,c,d,e,,,", ',');
        assertThat(strings).containsExactly("a", "b", "c", "d", "e", "", "", "");

        String[] strings2 = StringSplitterUtils.split("a\tb\tc\td\te\t\t\t", '\t');
        assertThat(strings2).containsExactly("a", "b", "c", "d", "e", "", "", "");

        String[] strings3 = StringSplitterUtils.split("a|b|c|d|e|||", '|');
        assertThat(strings3).containsExactly("a", "b", "c", "d", "e", "", "", "");
    }

    @Test
    void splitTheArrayWithExpectedArraySizeAndNoTrim() {
        String[] strings1 = StringSplitterUtils.split("a,b,c,d,e", ',', 5);
        assertThat(strings1).containsExactly("a", "b", "c", "d", "e");

        String[] strings2 = StringSplitterUtils.split("a\tb\tc\td\te", '\t', 5);
        assertThat(strings2).containsExactly("a", "b", "c", "d", "e");

        String[] strings3 = StringSplitterUtils.split("a|b|c|d|e", '|', 5);
        assertThat(strings3).containsExactly("a", "b", "c", "d", "e");
    }

    @Test
    void splitTheArrayWithExpectedArraySizeAndContainsTrim() {
        String[] strings = StringSplitterUtils.split("a,b,c,d,e,,,", ',', 8);
        assertThat(strings).containsExactly("a", "b", "c", "d", "e", "", "", "");

        String[] strings2 = StringSplitterUtils.split("a\tb\tc\td\te\t\t\t", '\t', 8);
        assertThat(strings2).containsExactly("a", "b", "c", "d", "e", "", "", "");

        String[] strings3 = StringSplitterUtils.split("a|b|c|d|e|||", '|', 8);
        assertThat(strings3).containsExactly("a", "b", "c", "d", "e", "", "", "");
    }

    @Test
    void splitWithABiggerArraySizeDefinition() {
        assertThat(assertThrows(
            IllegalArgumentException.class,
            () -> StringSplitterUtils.split("a,b,c,d,e,,,", ',', 9)
        )).hasMessage("The split str array was not equal to the given size.");
    }

    @Test
    void splitWithASmallerArraySizeDefinition() {
        assertThat(assertThrows(
            IllegalArgumentException.class,
            () -> StringSplitterUtils.split("a,b,c,d,e,,,", ',', 6)
        )).hasMessage("The split str array's length was exceed the given size.");
    }

    @Test
    void splitAStrWithAGivenStringDelimiter() {
        String[] strings = StringSplitterUtils.split("a,b,c,d,e,,,", ",");
        assertThat(strings).containsExactly("a", "b", "c", "d", "e", "", "", "");

        String[] strings1 = StringSplitterUtils.split("a..b..c..d..e......", "..");
        assertThat(strings1).containsExactly("a", "b", "c", "d", "e", "", "", "");

        String[] strings2 = StringSplitterUtils.split("a^^b^^c^^d^^e^^f", "^^");
        assertThat(strings2).containsExactly("a", "b", "c", "d", "e", "f");
    }

    @Test
    void splitAStrWithGivenStringDelimiterAndArrayLength() {
        String[] strings = StringSplitterUtils.split("a,b,c,d,e,,,", ",", 8);
        assertThat(strings).containsExactly("a", "b", "c", "d", "e", "", "", "");

        String[] strings1 = StringSplitterUtils.split("a,,b,,c,,d,,e,,,,,,", ",,", 8);
        assertThat(strings1).containsExactly("a", "b", "c", "d", "e", "", "", "");

        String[] strings2 = StringSplitterUtils.split("a^^b^^c^^d^^e^^f", "^^", 6);
        assertThat(strings2).containsExactly("a", "b", "c", "d", "e", "f");

        String[] strings3 = StringSplitterUtils.split("a^^b^^c^^d^^e^^^^f", "^^", 7);
        assertThat(strings3).containsExactly("a", "b", "c", "d", "e", "", "f");
    }

    @Test
    void splitTheStrWithTheDelimiterCharAndWrapChar() {
        String[] strings = StringSplitterUtils.split("a,\",,b\",c,,d,e,", ',', '"');
        assertThat(strings).containsExactly("a", "\",,b\"", "c", "", "d", "e", "");
    }

    @Test
    void splitTheStrWithTheDelimiterCharWrapCharAndLength() {
        String[] strings = StringSplitterUtils.split("a,\",,b\",c,,d,e,", ',', '"', 7);
        assertThat(strings).containsExactly("a", "\",,b\"", "c", "", "d", "e", "");
    }

    @Test
    void splitTheStrWithTheDelimiterStrWrapStrAndLengthFirstCase() {
        String[] strings = StringSplitterUtils.split("a||b2||c2||d||e||f||g", "||", "||", 4);
        assertThat(strings).containsExactly("a||b2||c2||d||e||f||g");
    }

    @Test
    void splitTheStrWithTheDelimiterStrWrapStrAndLengthSecondCase() {
        String[] strings = StringSplitterUtils.split("a,\",,b\",c,,d,e,", ",", "\"", 7);
        assertThat(strings).containsExactly("a", "\",,b\"", "c", "", "d", "e", "");
    }

    @Test
    void splitTheStrWithTheDelimiterStrWrapStrAndLengthThirdCase() {
        String[] strings = StringSplitterUtils.split("a,jj\"\",,b\"\"dd,c,,d,e,", ",", "\"\"", 7);
        assertThat(strings).containsExactly("a", "jj\"\",,b\"\"dd", "c", "", "d", "e", "");
    }

    @Test
    void splitTheStrWithTheDelimiterStrWrapStrAndLengthFourthCase() {
        String[] strings = StringSplitterUtils.split("jj\"\",,b\"\"dd,c,,d", ",", "\"\"", 4);
        assertThat(strings).containsExactly("jj\"\",,b\"\"dd", "c", "", "d");
    }

    @Test
    void splitTheStrWithTheDelimiterStrWrapStrAndLengthFifthCase() {
        String[] strings = StringSplitterUtils.split("a,jj\"\",,b\"\"dd,c,,d,e,,jj\"\",,b\"\"dd", ",", "\"\"", 8);
        assertThat(strings).containsExactly("a", "jj\"\",,b\"\"dd", "c", "", "d", "e", "", "jj\"\",,b\"\"dd");
    }

    @Test
    void splitTheStrWithTheDelimiterStrWrapStrAndLengthSixthCase() {
        String[] strings = StringSplitterUtils.split("jj\"\",,b\"\"dd", ",", "\"\"", 1);
        assertThat(strings).containsExactly("jj\"\",,b\"\"dd");
    }

    @Test
    void splitTheStrWithTheDelimiterStrWrapStrAndLengthSeventhCase() {
        String[] strings = StringSplitterUtils.split(",jj\"\",,b\"\"dd", ",", "\"\"", 2);
        assertThat(strings).containsExactly("", "jj\"\",,b\"\"dd");
    }
}
