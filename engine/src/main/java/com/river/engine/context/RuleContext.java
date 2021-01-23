package com.river.engine.context;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.river.engine.runtime.partial.MetricFilter;
import com.river.engine.runtime.partial.PartAliasInfo;
import com.river.engine.runtime.partial.PartFromInfo;
import com.river.engine.runtime.partial.PartSplitInfo;
import com.river.engine.ast.Operand;
import com.river.engine.enmu.AliasType;
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

    private List<PartAliasInfo> aliasInfos = Lists.newArrayList();

    private MetricFilter metricFilter;

    private PartFromInfo fromInfo;

    private PartSplitInfo splitInfo;


    /**
     * Make {@code AS} could be used in future DSL
     * @param aliasType the alias type @link{AliasType}
     * @param aliasName   The  alias name
     * @param operand The operand alias
     */
    public void registerOperand(AliasType aliasType,  String aliasName, Operand operand) {
        if (!Strings.isNullOrEmpty(aliasName)) {
            if (containsKey(aliasName)) {
                throw new IllegalArgumentException("Duplicated alias name [" + aliasName + "] in your rule sql.");
            } else {
                aliasInfos.add(new PartAliasInfo(aliasType, aliasName, operand));
            }
        }
    }


    private boolean containsKey(String alias){
        List<PartAliasInfo> containsList = aliasInfos.stream().filter(s -> alias.equals(s.getAliasName())).collect(Collectors.toList());
        if(containsList.size()>=1 ){
            return true;
        }
        return false;
    }


}
