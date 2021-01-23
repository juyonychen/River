package com.river.engine.dsl.handler;

/**
 * @author Juyony Chen
 * @version 1.0.0, 2020/9/14 19:09
 * @since 1.0.0, 2020/9/14 19:09
 **/
public class ErrorCommon {

    public static String underlineError(String fullText, String symbolText, int line, int charPositionInLine) {
        StringBuilder buffer = new StringBuilder("\n\n");

        buffer.append(errorLine(fullText, line));
        buffer.append("\n");

        for (int i = 0; i < charPositionInLine; i++) {
            buffer.append(" ");
        }

        for (int i = 0; i < symbolText.length(); i++) {
            buffer.append("^");
        }

        buffer.append("\n");
        return buffer.toString();
    }

    private static String errorLine(String errorMessage, int lineNumber) {
        int startIndex = 0;
        int endIndex = 0;
        for (int i = 0; i < lineNumber; i++) {
            int index = errorMessage.indexOf('\n', endIndex + 1);
            if (i > 0) {
                startIndex = endIndex + 1;
            }
            if (index >= 0) {
                endIndex = index;
            } else {
                endIndex = errorMessage.length();
            }
        }
        return errorMessage.substring(startIndex, endIndex);
    }
}
