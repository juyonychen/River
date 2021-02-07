package com.river.engine.context;

import com.google.common.collect.Lists;
import com.river.engine.runtime.partial.MetricFilter;
import com.river.engine.runtime.partial.PartFromInfo;
import com.river.engine.runtime.partial.PartSplitInfo;
import com.river.engine.ast.Operand;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Juyony Chen
 * @version 1.0.0, 2020/9/15 20:48
 * @since 1.0.0, 2020/9/15 20:48
 **/
@Slf4j
@Data
public class RuleContext implements Serializable {
    private static final long serialVersionUID = 178700400335747540L;

    private List<Operand> aliasOperands = Lists.newArrayList();

    private MetricFilter metricFilter;

    private PartFromInfo fromInfo;

    private String splitInfo;

    /**
     * Make {@code AS} could be used in future DSL
     * @param operand The operand alias
     */
    public void registerOperand(Operand operand) {
        if (operand != null) {
            if (containsKey(operand.toString())) {
                throw new IllegalArgumentException("Duplicated alias name [" + operand.toString() + "] in your rule sql.");
            } else {
                aliasOperands.add(operand);
            }
        }
    }


    private boolean containsKey(String alias){
        List<Operand> containsList = aliasOperands.stream().filter(s -> alias.equals(s.toString())).collect(Collectors.toList());
        if(containsList.size()>=1 ){
            return true;
        }
        return false;
    }


}
