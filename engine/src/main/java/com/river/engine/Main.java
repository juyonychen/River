package com.river.engine;

import com.river.common.dto.RuleDTO;
import com.river.engine.runtime.RuleParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Juyony Chen
 * @version 1.0.0, 2021/1/28 17:56
 * @since 1.0.0, 2021/1/28 17:56
 **/
public class Main {
    public static void main(String[] args) {


        RuleDTO ruleDTO = new RuleDTO();
        List<String> sql = new ArrayList<>();
        sql.add(" select String(rule) as rule, String(oper_type) as oper_type, Long(createTime) as createTime from json ");
        sql.add(" select String(rule_id)as rule_id, Int(calculate_interval) as calculate_interval, String(alert_sql) as alert_sql, String(metricCodes)as metricCodes, oper_type, createTime, String(target_sql) as target_sql from json(\"rule\")");
        sql.add("select rule_id, String(WARNING) as warning, String(CRITICAL)as critical, alert_sql,metricCodes, oper_type, createTime, target_sql from json(\"alert_sql\")");
        sql.add("select rule_id, warning, critical, String(0) as splitString, metricCodes,oper_type, createTime from split(\"target_sql\") splitby \"\\|\"");
        sql.add("select rule_id, warning, critical, Int(1) as Num , metricCodes,oper_type, createTime from split(\"splitString\") splitby \"\\#\"");
        sql.add("select rule_id, warning, critical, Num , String(metricCode)as metricCode, Int(continueMinutes)as continueMinutes,oper_type, createTime from jsonList(\"metricCodes\") ");
        sql.add("select rule_id, warning, critical, Int(0) as Num_free, metricCode, continueMinutes,oper_type, createTime from splitList(\"splitString\") splitby \"\\#\" ");
        ruleDTO.setParserSqls(sql);
        RuleParser ruleParser = new RuleParser(ruleDTO);
        ruleParser.init();


        List<Map<String , String>> result = ruleParser.calculate(Message.generator());
//        long start = System.currentTimeMillis();
//        for(int x  =1; x <=1000000; x ++){
//            Map<String, String> result = ruleParser.calculate(Message.generator());
//        }
//
//        System.out.println(System.currentTimeMillis()-start);
        System.out.println("result："+result.toString());
    }
}
