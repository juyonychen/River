package com.river.engine.runtime;

import com.river.common.dto.MetaDataInfo;
import com.river.common.dto.RuleDTO;
import com.river.common.dto.enums.DataType;
import com.river.common.utils.JSONUtils;
import com.river.engine.ast.Operand;
import com.river.engine.ast.operands.FunctionOperand;
import com.river.engine.ast.operands.operand.ColumnOperand;
import com.river.engine.ast.operands.operand.NamingOperand;
import com.river.engine.dsl.exception.UnSupportFunctionException;
import com.river.engine.dsl.parser.impl.CalculateParser;
import com.river.engine.runtime.processor.CalculateProcessor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Juyony Chen
 * @version 1.0.0, 2020/11/30 17:51
 * @since 1.0.0, 2020/11/30 17:51
 **/
@Slf4j
@Data
public class RuleParser {
    private RuleDTO ruleDTO;

    private List<CalculateProcessor> processorList;
    private List<MetaDataInfo> metadata = new ArrayList<>();

    public RuleParser(RuleDTO ruleDTO){

        if(ruleDTO.getParserSqls().size() < 1){
            throw new UnSupportFunctionException("The ruleDto must contain parserSQL ：{}", JSONUtils.INSTANCE.toJson(ruleDTO));
        }
        this.ruleDTO = ruleDTO;
    }

    public void init(){
        processorList = parser(ruleDTO.getParserSqls());
        initMetaDataInfo();
    }

    public List<Map<String , String>> calculate(String message){

        List<Map<String , String>> contexts = new ArrayList<>();
        List<Map<String , String>> results ;
        for(int x = 0; x < processorList.size(); x ++){
            CalculateProcessor processor = processorList.get(x);
            if(x == 0){
                results = processor.calculate(message, null);
                transContext(contexts, results);
            }else{
                int forLength = contexts.size();
                for(int y = 0; y < forLength; y ++){
                    results = processor.calculate(message, contexts.get(0));
                    transContext(contexts, results);
                    contexts.remove(0);
                }
            }
            log.info("Context is:{}", contexts.toString());
        }

        return contexts;
    }


    /**
     *
     * @param contexts
     * @param results
     */
    private void transContext(List<Map<String , String>> contexts, List<Map<String , String>> results){
        results.stream().forEach(result ->{
            Map<String , String> cache;
            if(contexts.size() > 0){
                cache = new HashMap<>(contexts.get(0));
            }else{
                cache = new HashMap<>();
            }
            cache.putAll(result);
            contexts.add(cache);
        });
    }

    /**
     *
     * @return
     */
    public void initMetaDataInfo(){
        // 按照顺序获取全部的Funtion-Operand
        Map<String, DataType> allFunctionOperand = new HashMap<>();
        processorList.stream().forEach(processor ->
                processor.getRuleContext().getAliasOperands().stream().forEach(operand ->{
                    if(operand instanceof NamingOperand){
                        Operand functionOperand = ((NamingOperand) operand).getNamingOperand();
                        allFunctionOperand.put(((NamingOperand) operand).getNamingName(), ((FunctionOperand) functionOperand).getDataType());
                    } }));

        CalculateProcessor lastProcessor = processorList.get(processorList.size()-1);
        lastProcessor.getRuleContext().getAliasOperands().stream().forEach(operand -> {
            if(operand instanceof ColumnOperand){
                metadata.add(new MetaDataInfo(operand.toString(), allFunctionOperand.get(operand.toString()) ));
            }else if(operand instanceof NamingOperand){
                metadata.add(new MetaDataInfo(operand.toString(), ((FunctionOperand) ((NamingOperand) operand).getNamingOperand()).getDataType() ));
            }
        });

        log.info("MetaData Message is:{}", JSONUtils.INSTANCE.toJson(metadata));
    }

    /**
     *
     * @param dsls
     * @return
     */
    public List<CalculateProcessor> parser(List<String> dsls) {
        List<CalculateProcessor> processorList = new ArrayList<>();
        for(int x =0; x <dsls.size(); x++){
            processorList.add(this.parser(dsls.get(x)));
        }
        return processorList;
    }

    /**
     *
     * @param parserDsl
     * @return
     */
    public CalculateProcessor parser( String parserDsl) {
        CalculateParser processor = new CalculateParser();
        return processor.parser(parserDsl);
    }

}


