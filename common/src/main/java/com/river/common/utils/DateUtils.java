
package com.river.common.utils;

import com.river.common.log.SampleLogFactory;
import com.river.common.time.ClockUtils;
import lombok.experimental.UtilityClass;
import org.slf4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.lang.Long.valueOf;
import static org.apache.commons.lang3.StringUtils.*;
import static org.apache.commons.lang3.time.DateUtils.addMinutes;

/**
 * euphoria: DateUtils
 *
 * @author wangsixian (Employee ID: 88420576)
 * @version 1.2.0, 2019-03-28 17:34
 * @since 1.2.0, 2019-03-28 17:34
 */
@UtilityClass
public class DateUtils {

    private static final Logger log = SampleLogFactory.getLogger(DateUtils.class);
    public static final String ERROR_DATE = "0000-00-00 00:00:00";
    public static final String DATE_TYPE_TIMESTAMP = "timestamp";
    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN = "yyyyMMddHHmmss";
    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_PATTERN);

    public static String parseDate(String strDate, String format, String locale) {
        final DateFormat defaultFormat = new SimpleDateFormat(DEFAULT_PATTERN);
        try {
            if (equalsIgnoreCase(format, DATE_TYPE_TIMESTAMP)) {
                Long dateLong = valueOf(strDate);
                return defaultFormat.format(new Date(dateLong));
            } else {
                DateFormat df = locale == null ? new SimpleDateFormat(format, Locale.US) : new SimpleDateFormat(format, new Locale(locale));
                Date date = df.parse(strDate);
                return defaultFormat.format(date);
            }
        } catch (Exception e) {
            log.error("parseDate error", e);
            return ERROR_DATE;
        }
    }

    public static String getLastAlertTime(String time) {
        final DateFormat defaultFormat = new SimpleDateFormat(DEFAULT_PATTERN);
        if (isNotEmpty(time)) {
            long timeStamp = Long.parseLong(time);
            return defaultFormat.format(new Date(timeStamp));
        }
        return "";
    }

    public static String parseDateToMinute(String strDate, String originFormat, String outFormat, String locale) {
        DateFormat origin = locale == null ? new SimpleDateFormat(originFormat) : new SimpleDateFormat(originFormat, Locale.US);
        Date date = null;
        try {
            date = origin.parse(strDate);
        } catch (ParseException e) {
            log.error("parseDate error", e);
        }
        final DateFormat out = new SimpleDateFormat(outFormat);
        return out.format(date);
    }

    public static String getDay() {
        final DateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(new Date());
    }

    public static int getDiffDay(String strDate) {
        if (isEmpty(strDate)) {
            return -1;
        }

        final DateFormat format = new SimpleDateFormat(DEFAULT_PATTERN);
        Date eventTime;
        try {
            eventTime = format.parse(strDate);
        } catch (ParseException e) {
            return -1;
        }

        Date now = new Date();

        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(eventTime);
        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
        aCalendar.setTime(now);
        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);

        return day2 - day1;
    }

    public static int getDiffDay(String strDate, String format, String locale) {
        final DateFormat defaultFormat = new SimpleDateFormat(DEFAULT_PATTERN);
        String date;

        if (equalsIgnoreCase(format, DATE_TYPE_TIMESTAMP)) {
            Long dateLong = valueOf(strDate);
            Date eventTime = new Date(dateLong);
            date = defaultFormat.format(eventTime);
        } else {
            DateFormat df = locale == null ? new SimpleDateFormat(format) : new SimpleDateFormat(format, Locale.US);
            try {
                date = defaultFormat.format(df.parse(strDate));
            } catch (ParseException e) {
                return -1;
            }
        }
        return getDiffDay(date);
    }

    public static String getCurTime() {
        final DateFormat format = new SimpleDateFormat(PATTERN);
        return format.format(new Date());
    }

    public static String getCurTime(String format) {
        final DateFormat df = new SimpleDateFormat(format);
        return df.format(new Date());
    }

    public static String formatTime(Date time, String pattern) {
        final DateFormat df = new SimpleDateFormat(pattern);
        return df.format(time);
    }

    public static String getLastMinute(String format) {
        final DateFormat df = new SimpleDateFormat(format);
        Date date = addMinutes(new Date(), -2);
        return df.format(date);
    }

    public static String getLastMinuteFormat(String format) {
        final DateFormat df = new SimpleDateFormat(format);
        long timeMillis = ClockUtils.currentTimeMillis();
        Date date = addMinutes(new Date(timeMillis - timeMillis % 60000), -2);
        return df.format(date);
    }

    public static Date getDate(String date) {
        final DateFormat format = new SimpleDateFormat(PATTERN);
        try {
            return format.parse(date);
        } catch (ParseException e) {
            log.error("parseDate error", e);
            return null;
        }
    }

    public static Date getDate(String date, String pattern) {
        final DateFormat format = new SimpleDateFormat(pattern);
        try {
            return format.parse(date);
        } catch (ParseException e) {
            log.error("parseDate error", e);
            return null;
        }
    }

    public static Date getDateFromEpochMilli(Long epochMilli) {
        return Date.from(Instant.ofEpochMilli(epochMilli));
    }

    public static Date getDateFromEpochSecond(Long epochSecond) {
        return Date.from(Instant.ofEpochSecond(epochSecond));
    }

    public static String formatDate(Date date) {
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return DEFAULT_FORMATTER.format(localDateTime);
    }

}
