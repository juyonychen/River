
package com.river.engine.ast;

import com.google.common.base.Strings;
import com.river.engine.context.DataContext;
import com.river.engine.context.RuleContext;
import com.river.engine.dsl.exception.LexicalErrorException;
import com.river.engine.formats.types.Value;

import java.io.Serializable;


public interface Operand extends Serializable {


    /**
     * Calculate the extracted metric message in CEP module.
     *
     * @param metric    The latest metric message
     * @param context    The engine context for aggregation function
     */
    Value calculate(DataContext metric, RuleContext context);

    /**
     * Calculate the {@link DataContext} in ETL module, if it matches, we would extract the
     *
     * @param context    The metric context for a passed metric, the metric could be json, log, jmx, etc.
     */
    Value calculate(DataContext context);


    default void validateString(String str) {
        if (Strings.isNullOrEmpty(str)) {
            throw new LexicalErrorException("String evaluate shouldn't be null or empty");
        }
    }
}
