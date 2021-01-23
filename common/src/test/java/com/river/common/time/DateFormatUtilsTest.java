/*
 *
 *
 *
 *
 *
 */
package com.river.common.time;


import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.Duration;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test for formatter date
 *
 *
 * @version 1.0.0, 2018-02-27 20:09
 * @since 1.0.0, 2018-02-27 20:09
 */
@SuppressWarnings({"squid:CallToDeprecatedMethod", "squid:S1192"})
class DateFormatUtilsTest {

    @Test
    void isoDateFormat() {
        Date date = new Date(1477974224000L);
        assertThat(DateFormatUtils.ISO_FORMAT.format(date)).contains("2016-11-01T12:23:44.000");
        assertThat(DateFormatUtils.formatDate(DateFormatUtils.PATTERN_ISO, date)).contains("2016-11-01T12:23:44.000");
        assertThat(DateFormatUtils.ISO_ON_SECOND_FORMAT.format(date)).contains("2016-11-01T12:23:44");
        assertThat(DateFormatUtils.formatDate(DateFormatUtils.PATTERN_ISO_ON_SECOND, date)).contains("2016-11-01T12:23:44");
        assertThat(DateFormatUtils.ISO_ON_DATE_FORMAT.format(date)).isEqualTo("2016-11-01");
        assertThat(DateFormatUtils.formatDate(DateFormatUtils.PATTERN_ISO_ON_DATE, date)).isEqualTo("2016-11-01");
    }

    @Test
    void defaultDateFormat() {
        Date date = new Date(1477974224000L);
        assertThat(DateFormatUtils.DEFAULT_FORMAT.format(date)).isEqualTo("2016-11-01 12:23:44.000");
        assertThat(DateFormatUtils.DEFAULT_ON_SECOND_FORMAT.format(date)).isEqualTo("2016-11-01 12:23:44");
        assertThat(DateFormatUtils.formatDate(DateFormatUtils.PATTERN_DEFAULT_ON_SECOND, date)).isEqualTo("2016-11-01 12:23:44");
    }

    @Test
    void formatWithPattern() {
        Date date = new Date(1477974224000L);
        assertThat(DateFormatUtils.formatDate(DateFormatUtils.PATTERN_DEFAULT, date))
            .isEqualTo("2016-11-01 12:23:44.000");
        assertThat(DateFormatUtils.formatDate(DateFormatUtils.PATTERN_DEFAULT, date.getTime()))
            .isEqualTo("2016-11-01 12:23:44.000");
    }

    @Test
    void parseWithPattern() throws ParseException {
        Date date = new Date(1477974224000L);
        Date resultDate = DateFormatUtils.parseDate(DateFormatUtils.PATTERN_DEFAULT, "2016-11-01 12:23:44.000");
        assertThat(resultDate.getTime() == date.getTime()).isTrue();
    }

    @Test
    void formatDuration() {
        assertThat(DateFormatUtils.formatDuration(100)).isEqualTo("00:00:00.100");

        assertThat(DateFormatUtils.formatDuration(new Date(100), new Date(3000))).isEqualTo("00:00:02.900");

        assertThat(DateFormatUtils.formatDuration(Duration.ofDays(2).toMillis() + Duration.ofHours(4).toMillis()))
            .isEqualTo("52:00:00.000");

        assertThat(DateFormatUtils.formatDurationOnSecond(new Date(100), new Date(3000))).isEqualTo("00:00:02");

        assertThat(DateFormatUtils.formatDurationOnSecond(2000)).isEqualTo("00:00:02");

        assertThat(DateFormatUtils.formatDurationOnSecond(Duration.ofDays(2).toMillis() + Duration.ofHours(4).toMillis()))
            .isEqualTo("52:00:00");
    }
}
