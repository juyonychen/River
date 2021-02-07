/*
 * Copyright © 2013-2018 Suning.com Co., Ltd.
 *
 * This file is part of euphoria project.
 * It can not be copied and/or distributed without the express
 * permission of cloudytrace group.
 */
package com.river.engine.formats.types.utils;

import com.river.engine.formats.types.values.BooleanValue;
import com.river.engine.formats.types.values.NullValue;
import com.river.engine.formats.types.values.NumberValue;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * A constant class which holds immutable constant value, use this class for singleton purpose.
 *
 */
@UtilityClass
@Slf4j
public class ValueConstants {

    /**
     * Null value don't have multiple instance, use this instance is enough
     */
    public static final NullValue NULL_VALUE = new NullValue();

    public static final BooleanValue TRUE_VALUE = new BooleanValue(true);

    public static final BooleanValue FALSE_VALUE = new BooleanValue(false);

    /**
     * This would be used in count operand, reduce the class creation
     */
    public static final NumberValue NUMBER_ONE = new ConstantNumberValue(1);

    /**
     * NaN is a immutable constant number
     */
    public static final NumberValue NAN_VALUE = new ConstantNumberValue(Double.NaN);

    /**
     * The calculate precise for operands
     */
    public static final double OPERAND_CALCULATE_PRECISE;

    static {
        OPERAND_CALCULATE_PRECISE =  Math.pow(0.1, 10);
    }



    public static final class ConstantNumberValue extends NumberValue {
        private static final long serialVersionUID = -2018319831399869636L;

        // Make it package protect
        ConstantNumberValue(double constantValue) {
            super(constantValue);
        }

        @Override
        public void updateNumber(double value) {
            throw new UnsupportedOperationException("Constant NumberValue don't support update operation.");
        }
    }
}
