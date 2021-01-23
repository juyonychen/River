
package com.river.common.utils;


import com.river.common.exception.TypeCastException;
import com.river.common.time.DateFormatUtils;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * A field convert helper class inspired by fastjson type utils.
 * This class isn't written by myself, it may have poorly condition.
 */
@UtilityClass
@SuppressWarnings({"squid:MethodCyclomaticComplexity", "squid:S3776"})
public class TypeUtils {

    public static String castToString(Object value) {
        if (value == null) {
            return null;
        }

        return value.toString();
    }

    public static Byte castToByte(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Number) {
            return ((Number) value).byteValue();
        }

        if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.length() == 0) {
                return null;
            }
            return Byte.parseByte(strVal);
        }

        throw new TypeCastException(errorMessage("int", value));
    }

    public static Short castToShort(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Number) {
            return ((Number) value).shortValue();
        }

        if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.length() == 0) {
                return null;
            }
            return Short.parseShort(strVal);
        }

        throw new TypeCastException(errorMessage("short", value));
    }

    public static BigDecimal castToBigDecimal(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        }

        if (value instanceof BigInteger) {
            return new BigDecimal((BigInteger) value);
        }

        String strVal = value.toString();
        if (strVal.length() == 0) {
            return null;
        }

        return new BigDecimal(strVal);
    }

    public static BigInteger castToBigInteger(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof BigInteger) {
            return (BigInteger) value;
        }

        if (value instanceof Float || value instanceof Double) {
            return BigInteger.valueOf(((Number) value).longValue());
        }

        String strVal = value.toString();
        if (strVal.length() == 0) {
            return null;
        }

        return new BigInteger(strVal, 10);
    }

    public static Float castToFloat(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Number) {
            return ((Number) value).floatValue();
        }

        if (value instanceof String) {
            String strVal = value.toString();
            if (strVal.length() == 0) {
                return null;
            }

            return Float.parseFloat(strVal);
        }

        throw new TypeCastException(errorMessage("float", value));
    }

    public static Double castToDouble(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }

        if (value instanceof String) {
            String strVal = value.toString();
            if (strVal.length() == 0) {
                return null;
            }
            return Double.parseDouble(strVal);
        }

        throw new TypeCastException(errorMessage("double", value));
    }

    public static Date castToDate(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Calendar) {
            return ((Calendar) value).getTime();
        }

        if (value instanceof Date) {
            return (Date) value;
        }

        long longValue = -1;

        if (value instanceof Number) {
            longValue = ((Number) value).longValue();
        }

        if (value instanceof String) {
            String strVal = (String) value;

            if (strVal.length() == 0) {
                return null;
            }

            if (strVal.indexOf('-') != -1) {
                String format;
                if (strVal.length() == 10) {
                    format = "yyyy-MM-dd";
                } else if (strVal.length() == "yyyy-MM-dd HH:mm:ss".length()) {
                    format = "yyyy-MM-dd HH:mm:ss";
                } else {
                    format = "yyyy-MM-dd HH:mm:ss.SSS";
                }

                try {
                    return DateFormatUtils.parseDate(format, strVal);
                } catch (ParseException e) {
                    throw new TypeCastException(errorMessage("Date", value));
                }
            }

            longValue = Long.parseLong(strVal);
        }

        if (longValue < 0) {
            throw new TypeCastException(errorMessage("Date", value));
        }

        return new Date(longValue);
    }

    public static java.sql.Date castToSqlDate(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Calendar) {
            return new java.sql.Date(((Calendar) value).getTimeInMillis());
        }

        if (value instanceof java.sql.Date) {
            return (java.sql.Date) value;
        }

        if (value instanceof Date) {
            return new java.sql.Date(((Date) value).getTime());
        }

        long longValue = castToLong(value);

        if (longValue <= 0) {
            throw new TypeCastException(errorMessage("Date", value));
        }

        return new java.sql.Date(longValue);
    }

    public static Timestamp castToTimestamp(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Calendar) {
            return new Timestamp(((Calendar) value).getTimeInMillis());
        }

        if (value instanceof Timestamp) {
            return (Timestamp) value;
        }

        if (value instanceof Date) {
            return new Timestamp(((Date) value).getTime());
        }

        long longValue = castToLong(value);

        if (longValue <= 0) {
            throw new TypeCastException(errorMessage("Date", value));
        }

        return new Timestamp(longValue);
    }

    public static Long castToLong(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Number) {
            return ((Number) value).longValue();
        }

        if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.length() == 0) {
                return null;
            }

            try {
                return Long.parseLong(strVal);
            } catch (NumberFormatException ex) {
                try {
                    return ((long) (Double.parseDouble(strVal)));
                } catch (NumberFormatException e) {
                    throw new TypeCastException(errorMessage("long", value), e);
                }
            }
        }

        throw new TypeCastException(errorMessage("long", value));
    }

    public static Integer castToInt(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Integer) {
            return (Integer) value;
        }

        if (value instanceof Number) {
            return ((Number) value).intValue();
        }

        if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.length() == 0) {
                return null;
            }

            return Integer.parseInt(strVal);
        }

        throw new TypeCastException(errorMessage("int", value));
    }

    @SuppressWarnings("squid:S2447")
    public static Boolean castToBoolean(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Boolean) {
            return (Boolean) value;
        }

        if (value instanceof Number) {
            return ((Number) value).intValue() == 1;
        }

        if (value instanceof String) {
            String str = ((String) value).toLowerCase();
            if (str.length() == 0) {
                return null;
            }

            if ("true".equalsIgnoreCase(str)) {
                return Boolean.TRUE;
            }
            if ("false".equalsIgnoreCase(str)) {
                return Boolean.FALSE;
            }

            if (isNumber(str)) {
                return "1".equals(str);
            }
        }

        throw new TypeCastException(errorMessage("boolean", value));
    }

    @SuppressWarnings({"unchecked", "rawtypes", "squid:S134"})
    public static <T> T castToEnum(Object obj, Class<T> clazz) {
        try {
            if (obj instanceof String) {
                String name = (String) obj;
                if (name.length() == 0) {
                    return null;
                }

                return (T) Enum.valueOf((Class<? extends Enum>) clazz, name);
            }

            if (obj instanceof Number) {
                int ordinal = ((Number) obj).intValue();

                Method method = clazz.getMethod("values");
                Object[] values = (Object[]) method.invoke(null);
                for (Object value : values) {
                    Enum e = (Enum) value;
                    if (e.ordinal() == ordinal) {
                        return (T) e;
                    }
                }
            }
        } catch (Exception ex) {
            throw new TypeCastException(errorMessage(clazz, obj), ex);
        }

        throw new TypeCastException(errorMessage(clazz, obj));
    }

    public static Time castToSqlTime(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Time) {
            return (Time) value;
        }
        if (value instanceof Date) {
            return new Time(((Date) value).getTime());
        }
        if (value instanceof Calendar) {
            return new Time(((Calendar) value).getTimeInMillis());
        }
        long longValue = 0;
        if (value instanceof Number) {
            longValue = ((Number) value).longValue();
        }
        if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.length() == 0 //
                || "null".equalsIgnoreCase(strVal)) {
                return null;
            }
            if (isNumber(strVal)) {
                longValue = Long.parseLong(strVal);
            } else {
                throw new TypeCastException(errorMessage("Timestamp", strVal));
            }
        }
        if (longValue <= 0) {
            throw new TypeCastException(errorMessage("Date", value));
        }
        return new Time(longValue);
    }

    private static String errorMessage(Class typeClass, Object valueToCast) {
        return errorMessage(typeClass.getName(), valueToCast);
    }

    private static String errorMessage(String typeClassName, Object valueToCast) {
        return "can not cast to " + typeClassName + ", value : " + valueToCast;
    }

    public static boolean isNumber(String str) {
        for (int i = 0; i < str.length(); ++i) {
            char ch = str.charAt(i);
            if (ch == '+' || ch == '-') {
                if (i != 0) {
                    return false;
                }
            } else if (ch < '0' || ch > '9') {
                return false;
            }
        }
        return true;
    }
}
