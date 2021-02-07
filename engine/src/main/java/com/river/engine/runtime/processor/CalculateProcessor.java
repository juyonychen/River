package com.river.engine.runtime.processor;

import com.river.common.constant.SysConstants;
import com.river.common.dto.enums.FromType;
import com.river.engine.ast.operands.operand.NamingOperand;
import com.river.engine.context.DataContext;
import com.river.engine.context.DataContextFactory;
import com.river.engine.context.RuleContext;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.*;

/**
 *
 */
@Data
@Slf4j
@SuppressWarnings("squid:S1948")
public class CalculateProcessor implements Serializable{
    private static final long serialVersionUID = 1935420237768485519L;

    /**
     * ruleContext used for storage parse operand and alias
     */
    private RuleContext ruleContext;

    /**
     *
     * @param message
     * @return
     */
    public List<Map<String, String>> calculate(String message, Map<String, String> context){
        DataContext dataContext = DataContextFactory.generatorDataContex(ruleContext.getFromInfo(), message, ruleContext.getSplitInfo(), context);
        Optional<Map<String, String>> result = calculate(dataContext, context);
        List<Map<String, String>> resultMap = new ArrayList<>();
        if( (ruleContext.getFromInfo().getFromType() == FromType.SPLITLIST || ruleContext.getFromInfo().getFromType() == FromType.JSONLIST)
                && result.isPresent()){
            //将result 拆成多条数据，返回list对象
            for(int x = 0 ; x< ruleContext.getAliasOperands().size(); x++){
                if(ruleContext.getAliasOperands().get(x) instanceof NamingOperand){
                    String namingName = ((NamingOperand) ruleContext.getAliasOperands().get(x)).getNamingName();
                    String[] operandSplit = result.get().get(namingName).split(String.valueOf(SysConstants.LIST_CHAR));
                    for(int y = 0; y<operandSplit.length; y++){
                        if(resultMap.size() < operandSplit.length){
                            resultMap.add(new HashMap<>(result.get()));
                        }
                        resultMap.get(y).put(namingName, operandSplit[y]);
                    }
                }
            }
        }else {
            resultMap.add(result.get());
        }
        return resultMap;
    }


    /**
     * @param dataContext
     * @return
     */
    private Optional<Map<String, String>> calculate(DataContext dataContext, Map<String, String> context){
        Map<String, String> result = new HashMap<>();
        if(ruleContext.getMetricFilter() !=null ){
            if(ruleContext.getMetricFilter().isDesiredMetric(dataContext)){
                for(int x = 0; x <ruleContext.getAliasOperands().size(); x ++){
                    result.put(ruleContext.getAliasOperands().get(x).toString(),
                            ruleContext.getAliasOperands().get(x).calculate(dataContext, context).toString());
                }
            }
        }else{
            for(int x = 0; x <ruleContext.getAliasOperands().size(); x ++){
                result.put(ruleContext.getAliasOperands().get(x).toString(),
                        ruleContext.getAliasOperands().get(x).calculate(dataContext, context).toString());
            }
        }
        return Optional.of(result);
    }

}
