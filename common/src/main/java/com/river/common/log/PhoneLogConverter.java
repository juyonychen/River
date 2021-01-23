
package com.river.common.log;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @since 7/3/2020
 */
public class PhoneLogConverter extends MessageConverter {

    private static final String REGEX = "[^\\d](1[3-9][0-9]{9})[^\\d]";

    private static final Pattern PATTERN = Pattern.compile(REGEX);

    private static final String D_NUM = "****";

    @Override
    public String convert(ILoggingEvent event) {
        String message = event.getFormattedMessage();
        Matcher m = PATTERN.matcher(message);
        StringBuilder sb = new StringBuilder(message);
        while (m.find()) {
            //使用分组进行替换
            sb.replace(m.start(1) + 3, m.end(1) - 4, D_NUM);
            m = PATTERN.matcher(sb);
        }
        return sb.toString();
    }

}
