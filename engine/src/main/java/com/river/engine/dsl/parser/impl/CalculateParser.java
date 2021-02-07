package com.river.engine.dsl.parser.impl;

import com.river.common.dto.enums.FromType;
import com.river.engine.ast.Expression;
import com.river.engine.context.RuleContext;
import com.river.engine.dsl.parser.AbstractRuleElementParser;
import com.river.engine.grammar.RuleSQLParser;
import com.river.engine.runtime.partial.MetricFilter;
import com.river.engine.runtime.partial.PartFromInfo;
import com.river.engine.runtime.processor.CalculateProcessor;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.List;

/**
 * @author Juyony Chen
 * @version 1.0.0, 2020/9/16 16:26
 * @since 1.0.0, 2020/9/16 16:26
 **/
public class CalculateParser extends AbstractRuleElementParser<CalculateProcessor> {

    public CalculateParser(){
        this.ruleContext = new RuleContext();
    }


    @Override
    public CalculateProcessor parser(String sql) {

        RuleSQLParser context = parseContext(sql);
        RuleSQLParser.CalculateStatementContext statementContext = context.calculateStatement();
        RuleSQLParser.FromtableContext fromtypeContext = statementContext.source().fromtable();
        RuleSQLParser.SplitByExprContext splitByExprContext = statementContext.splitByExpr();
        CalculateProcessor processor = new CalculateProcessor();
        if(statementContext.WHERE() !=null){
            Expression filterExpression = (Expression) visitFilterConditions(statementContext.filterConditions());
            MetricFilter metricFilter = new MetricFilter(filterExpression);

           ruleContext.setMetricFilter(metricFilter);
        }
        if(statementContext.FROM() != null){
            List<ParseTree> list = fromtypeContext.children;
            if(list.size() == 1){
                ruleContext.setFromInfo(new PartFromInfo(FromType.convert(list.get(0).getText()), ""));
            }else if(list.size() == 4){
                ruleContext.setFromInfo(new PartFromInfo(FromType.convert(list.get(0).getText()), removeQuote(list.get(2).getText())));
            }

        }
        if(statementContext.SPLITBY() != null){
            ruleContext.setSplitInfo(this.visitSplitByExpr(splitByExprContext));
        }
        visitMetrics(statementContext.metrics());
        processor.setRuleContext(ruleContext);
        return processor;
    }

}
