/*
 *
 *
 *
 *
 *
 */
package com.river.common.utils;


import com.river.common.time.ClockUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

/**
 *
 */
@SuppressWarnings("squid:S1192")
class TypeUtilsTest {

    @Test
    @DisplayName("Cast every type to string by toString method")
    void castToStringTest() {
        Assertions.assertThat(TypeUtils.castToString(null))
            .as("null would return null").isNull();
        Assertions.assertThat(TypeUtils.castToString(12))
            .as("int would be convert to Integer").isEqualTo("12");
    }

    @Test
    void castToByteTest() {
        Assertions.assertThat(TypeUtils.castToByte(null))
            .as("null would return null").isNull();
        Assertions.assertThat(TypeUtils.castToByte(12222))
            .as("Number instance use byte value, which could cause overflow").isEqualTo((byte) -66);
        Assertions.assertThat(TypeUtils.castToByte(""))
            .as("Blank string would return null").isNull();
        Assertions.assertThat(TypeUtils.castToByte("12"))
            .as("String would be parse by Byte.parseByte").isEqualTo((byte) 12);
    }

    @Test
    void castToShortTest() {
        Assertions.assertThat(TypeUtils.castToShort(null))
            .as("null would return null").isNull();
        Assertions.assertThat(TypeUtils.castToShort(12222222))
            .as("Number instance use short value, which could cause overflow").isEqualTo((short) 32526);
        Assertions.assertThat(TypeUtils.castToShort(""))
            .as("Blank string would return null").isNull();
        Assertions.assertThat(TypeUtils.castToShort("12222"))
            .as("String would be parse by Short.parseShort").isEqualTo((short) 12222);
    }

    @Test
    void castToBigDecimalTest() {
        Assertions.assertThat(TypeUtils.castToBigDecimal(null))
            .as("null would return null").isNull();

        BigDecimal decimal = new BigDecimal("12.1");
        assertThat(TypeUtils.castToBigDecimal(decimal) == decimal) // NOSONAR we need ==, not equals
            .as("would return the same instance if give castToBigDecimal a BigDecimal").isTrue();

        BigInteger bigInteger = new BigInteger("13", 10);
        Assertions.assertThat(TypeUtils.castToBigDecimal(bigInteger))
            .as("BigInteger would convert to BigDecimal").isEqualTo(new BigDecimal(bigInteger));
        Assertions.assertThat(TypeUtils.castToBigDecimal(""))
            .as("Blank string would return null").isNull();
        Assertions.assertThat(TypeUtils.castToBigDecimal("10")).isEqualTo(new BigDecimal("10"));
    }

    @Test
    void castToBigIntegerTest() {
        Assertions.assertThat(TypeUtils.castToBigInteger(null))
            .as("null would return null").isNull();

        BigInteger bigInteger = new BigInteger("12", 10);
        assertThat(TypeUtils.castToBigInteger(bigInteger) == bigInteger) // NOSONAR we need ==, not equals
            .as("would return the same instance if give castToBigInteger a BigInteger").isTrue();
        Assertions.assertThat(TypeUtils.castToBigInteger(10.1)).isEqualTo(BigInteger.valueOf(10));
        Assertions.assertThat(TypeUtils.castToBigInteger(""))
            .as("Blank string would return null").isNull();
        Assertions.assertThat(TypeUtils.castToBigInteger("10")).isEqualTo(new BigInteger("10"));
    }

    @Test
    void castToFloatTest() {
        Assertions.assertThat(TypeUtils.castToFloat(null))
            .as("null would return null").isNull();
        Assertions.assertThat(TypeUtils.castToFloat(12222222.1))
            .as("Number instance use float value").isCloseTo((float) 12222222.1, offset((float) 0.1));
        Assertions.assertThat(TypeUtils.castToFloat(""))
            .as("Blank string would return null").isNull();
        Assertions.assertThat(TypeUtils.castToFloat("12222222.1"))
            .as("String would be parse by Float.parseFloat")
            .isCloseTo((float) 12222222.1, offset((float) 0.1));

    }

    @Test
    void castToDoubleTest() {
        Assertions.assertThat(TypeUtils.castToDouble(null))
            .as("null would return null").isNull();
        Assertions.assertThat(TypeUtils.castToDouble(12222222.1))
            .as("Number instance use double value").isCloseTo(12222222.1, offset(0.1));
        Assertions.assertThat(TypeUtils.castToDouble(""))
            .as("Blank string would return null").isNull();
        Assertions.assertThat(TypeUtils.castToDouble("12222222.1"))
            .as("String would be parse by Double.parseDouble")
            .isCloseTo(12222222.1, offset(0.1));

    }

    @Test
    void castToDateTest() {
        Assertions.assertThat(TypeUtils.castToDate(null))
            .as("null would return null").isNull();

        Calendar calendar = Calendar.getInstance();
        Assertions.assertThat(TypeUtils.castToDate(calendar)).isEqualTo(calendar.getTime());

        Date date = new Date();
        assertThat(TypeUtils.castToDate(date) == date)
            .as("Date would return the same instance").isTrue();

        long timeMillis = ClockUtils.currentTimeMillis();
        Assertions.assertThat(TypeUtils.castToDate(timeMillis))
            .as("Number would be used as timestamp to create date").isEqualTo(new Date(timeMillis));
        Assertions.assertThat(TypeUtils.castToDate(String.valueOf(timeMillis)))
            .as("normal string would be parsed to long and used as timestamp").isEqualTo(new Date(timeMillis));
        Assertions.assertThat(TypeUtils.castToDate("2017-12-12"))
            .as("Support yyyy-MM-dd str").isEqualTo(new Date(1513008000000L));
        Assertions.assertThat(TypeUtils.castToDate("2017-12-12 12:43:12"))
            .as("Support yyyy-MM-dd HH:mm:ss str").isEqualTo(new Date(1513053792000L));
        Assertions.assertThat(TypeUtils.castToDate("2017-12-12 12:43:12.111"))
            .as("Support yyyy-MM-dd HH:mm:ss.SSS str").isEqualTo(new Date(1513053792111L));
        Assertions.assertThat(TypeUtils.castToDouble(""))
            .as("Blank string would return null").isNull();


    }

    @Test
    void castToSqlDateTest() {
        Assertions.assertThat(TypeUtils.castToSqlDate(null))
            .as("null would return null").isNull();

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        Assertions.assertThat(TypeUtils.castToSqlDate(calendar))
            .as("Calendar would be cast to SQL Date").isEqualTo(date)
            .isInstanceOf(java.sql.Date.class);
        assertThat(TypeUtils.castToSqlDate(sqlDate) == sqlDate)
            .as("SQL Date would return the same instance").isTrue();
        Assertions.assertThat(TypeUtils.castToSqlDate(date)).isEqualTo(date).isInstanceOf(java.sql.Date.class);
        Assertions.assertThat(TypeUtils.castToSqlDate(date.getTime() + "")).isEqualTo(date).isInstanceOf(java.sql.Date.class);
    }

    @Test
    void castToTimestampTest() {
        Assertions.assertThat(TypeUtils.castToTimestamp(null))
            .as("null would return null").isNull();

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        Timestamp timestamp = new Timestamp(date.getTime());

        Assertions.assertThat(TypeUtils.castToTimestamp(calendar)).isEqualTo(timestamp).isInstanceOf(Timestamp.class);
        assertThat(TypeUtils.castToTimestamp(timestamp) == timestamp)
            .as("Timestamp would return the same instance").isTrue();
        Assertions.assertThat(TypeUtils.castToTimestamp(date)).isEqualTo(timestamp).isInstanceOf(Timestamp.class);
        Assertions.assertThat(TypeUtils.castToTimestamp(date.getTime())).isEqualTo(timestamp).isInstanceOf(Timestamp.class);
    }

    @Test
    void castToLongTest() {
        Assertions.assertThat(TypeUtils.castToLong(null))
            .as("null would return null").isNull();
        Assertions.assertThat(TypeUtils.castToLong(1222222222222L))
            .as("Number instance use long value").isEqualTo(1222222222222L);
        Assertions.assertThat(TypeUtils.castToLong(""))
            .as("Blank string would return null").isNull();
        Assertions.assertThat(TypeUtils.castToLong("1222222222222"))
            .as("String would be parse by Long.parseLong").isEqualTo(1222222222222L);

    }

    @Test
    void castToIntTest() {
        Assertions.assertThat(TypeUtils.castToInt(null))
            .as("null would return null").isNull();

        Integer integer = 12222222;
        assertThat(TypeUtils.castToInt(integer) == integer)
            .as("Integer instance would just return").isTrue();
        Assertions.assertThat(TypeUtils.castToInt(Byte.valueOf("12")))
            .as("Number instance use long value").isEqualTo(12);
        Assertions.assertThat(TypeUtils.castToInt(""))
            .as("Blank string would return null").isNull();
        Assertions.assertThat(TypeUtils.castToInt("122222222"))
            .as("String would be parse by Integer.parseInt").isEqualTo(122222222);
    }


    @Test
    void castToBooleanTest() {
        Assertions.assertThat(TypeUtils.castToBoolean(null))
            .as("null would return null").isNull();
        Assertions.assertThat(TypeUtils.castToBoolean(Boolean.FALSE)).isFalse();
        Assertions.assertThat(TypeUtils.castToBoolean(1)).isTrue();
        Assertions.assertThat(TypeUtils.castToBoolean("false")).isFalse();
        Assertions.assertThat(TypeUtils.castToBoolean("False")).isFalse();
        Assertions.assertThat(TypeUtils.castToBoolean("true")).isTrue();
        Assertions.assertThat(TypeUtils.castToBoolean("True")).isTrue();
        Assertions.assertThat(TypeUtils.castToBoolean("1")).isTrue();
    }

    @Test
    void castToEnumTest() {
        Assertions.assertThat(TypeUtils.castToEnum("AAAAA", FuckTest.class)).isEqualTo(FuckTest.AAAAA);
        Assertions.assertThat(TypeUtils.castToEnum(1, FuckTest.class)).isEqualTo(FuckTest.BBBBB);
    }

    @Test
    void castToSqlTimeTest() {
        Assertions.assertThat(TypeUtils.castToSqlTime(null))
            .as("null would return null").isNull();

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        Time sqlTime = new Time(date.getTime());

        Assertions.assertThat(TypeUtils.castToSqlTime(calendar))
            .as("Calendar would be cast to SQL Date").isEqualTo(date)
            .isInstanceOf(Time.class);
        assertThat(TypeUtils.castToSqlTime(sqlTime) == sqlTime)
            .as("SQL Time would return the same instance").isTrue();
        Assertions.assertThat(TypeUtils.castToSqlTime(date)).isEqualTo(date).isInstanceOf(Time.class);
        Assertions.assertThat(TypeUtils.castToSqlTime(date.getTime() + "")).isEqualTo(date).isInstanceOf(Time.class);
    }

    @Test
    void isNumberTest() {
        Assertions.assertThat(TypeUtils.isNumber(randomAlphabetic(12))).isFalse();
        Assertions.assertThat(TypeUtils.isNumber(randomNumeric(12))).isTrue();
    }

    private enum FuckTest {
        AAAAA, BBBBB
    }
}
