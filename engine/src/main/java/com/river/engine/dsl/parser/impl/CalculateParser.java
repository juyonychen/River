package com.river.engine.dsl.parser.impl;

import com.river.engine.context.RuleContext;
import com.river.engine.dsl.parser.AbstractRuleElementParser;
import com.river.engine.grammar.RuleSQLParser;
import com.river.engine.runtime.partial.MetricFilter;
import com.river.engine.runtime.processor.MetricCalculateProcessor;
import com.river.engine.ast.Expression;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.List;

/**
 * @author Juyony Chen
 * @version 1.0.0, 2020/9/16 16:26
 * @since 1.0.0, 2020/9/16 16:26
 **/
public class CalculateParser extends AbstractRuleElementParser<MetricCalculateProcessor> {

    public CalculateParser(RuleContext ruleContext){
        this.ruleContext = ruleContext;
    }


    @Override
    public MetricCalculateProcessor parse(String sql) {

        RuleSQLParser context = parseContext(sql);
        RuleSQLParser.CalculateStatementContext statementContext = context.calculateStatement();
        RuleSQLParser.FromtableContext fromtypeContext = statementContext.source().fromtable();
        RuleSQLParser.SplitByExprContext splitByExprContext = statementContext.splitByExpr();
//        RuleSQLParser.MetricsContext metricsContext = statementContext.metrics();
//        RuleSQLParser.FilterConditionsContext filterConditionsContext = statementContext.filterConditions();

        MetricCalculateProcessor processor = new MetricCalculateProcessor();
        if(statementContext.WHERE() !=null){
            Expression filterExpression = (Expression) visitFilterConditions(statementContext.filterConditions());
            MetricFilter metricFilter = new MetricFilter(filterExpression);

           ruleContext.setMetricFilter(metricFilter);
        }
        if(statementContext.FROM() != null){
            List<ParseTree> list = fromtypeContext.children;
            if(list.size() == 1){
                System.out.println(list.get(0));
            }else if(list.size() == 4){
                System.out.println(list.get(0));
                System.out.println(list.get(2));

            }

        }
        if(statementContext.SPLITBY() != null){
            String splitBy = this.visitSplitByExpr(splitByExprContext);
        }
        visitMetrics(statementContext.metrics());
        processor.setRuleContext(ruleContext);

        return processor;
    }


    private static String sql1= "select rule_id, warning, critical, String(2) as splitString, metricCodes,oper_type, createTime from split(\"alert_sql\") where rule_id =\"123\" splitby \"|\" ";
    public static void main(String[] args) {
        CalculateParser parser = new CalculateParser(new RuleContext());

        parser.parse(sql1);
    }



}
