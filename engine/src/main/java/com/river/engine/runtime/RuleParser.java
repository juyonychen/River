package com.river.engine.runtime;

import com.river.common.dto.RuleDTO;
import com.river.engine.context.RuleContext;
import com.river.engine.dsl.parser.impl.CalculateParser;
import com.river.engine.runtime.processor.MetricCalculateProcessor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * @author Juyony Chen
 * @version 1.0.0, 2020/11/30 17:51
 * @since 1.0.0, 2020/11/30 17:51
 **/
@Slf4j
public class RuleParser {

    /**
     *
     * @param rule
     */
    public static synchronized void validateRule(@NonNull RuleDTO rule) {

    }

    /**
     *
     * @param rule
     * @return
     */
    public static synchronized Optional<MetricCalculateProcessor> generateMetricCalculateProcessor(@NonNull RuleDTO rule) {
        try {
            CalculateParser parser = new CalculateParser(new RuleContext());
            MetricCalculateProcessor processor =parser.parse(rule.getParserSqls().get(0));
            return Optional.of(processor);
        } catch (Exception e) {
            log.error("Metric Rule parsing exception, rule was {}",rule.getRule_id(), e);
            return Optional.empty();
        }
    }



}


