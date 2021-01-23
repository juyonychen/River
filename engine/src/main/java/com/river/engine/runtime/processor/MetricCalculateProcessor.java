package com.river.engine.runtime.processor;

import com.river.common.dto.RuleControlMessage;
import com.river.engine.context.MetricContext;
import com.river.engine.context.RuleContext;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * A calculator processor which creates from the rule definition
 */
@Data
@Slf4j
@SuppressWarnings("squid:S1948")
public class MetricCalculateProcessor implements Serializable{
    private static final long serialVersionUID = 1935420237768485519L;

    /**
     * ruleContext used for storage aggregate operand and alias
     */
    private RuleContext ruleContext;

    /**
     * for
     */
    private RuleControlMessage ruleControlMessage;


    /**
     * calculate target and storage in cache<Map>
     * @param context
     * @return
     */
    public Optional<Map> calculate(MetricContext context) {
        Map<String, Object> result = new HashMap<>();
//        if(ruleContext.getMetricFilter() !=null ){
//            if(ruleContext.getMetricFilter().isDesiredMetric(context)){
//                for(int x =0;x< ruleContext.getContextOperandRegister().size();x ++){
//                    result.put(String.valueOf(x), ruleContext.getContextOperandRegister().get(x).calculate(context));
//                }
//            }
//        }else{
//            for(int x =0;x< ruleContext.getContextOperandRegister().size();x ++){
//                result.put(String.valueOf(x), ruleContext.getContextOperandRegister().get(x).calculate(context));
//            }
//        }
        return Optional.of(result);
    }


    /**
     * calculate aggregate function per 1m
     * @param context
     * @return
     */
    public Map<String, Object> calculateAggFunction(MetricContext context) {
        Map<String, Object> result = new HashMap<>();

        return result;
    }




    /**
     * calculate is alert
     * @param context
     * @return
     */
    public boolean calculateAlert(MetricContext context) {
        if(ruleContext.getMetricFilter() !=null ){
            if(ruleContext.getMetricFilter().isDesiredMetric(context)){
                return true;
            }
        }
        return false;
    }

}
