package com.river.engine.dsl.parser;

import com.google.common.collect.Lists;
import com.river.engine.ast.Expression;
import com.river.engine.ast.FunctionManager;
import com.river.engine.ast.Operand;
import com.river.engine.ast.expressions.binary.AndExpression;
import com.river.engine.ast.expressions.binary.OrExpression;
import com.river.engine.ast.expressions.binary.ParenthesisExpression;
import com.river.engine.ast.expressions.compare.*;
import com.river.engine.ast.expressions.function.InExpression;
import com.river.engine.ast.expressions.function.LikeExpression;
import com.river.engine.ast.expressions.function.NotExpression;
import com.river.engine.ast.operands.base.AbstractFunctionOperand;
import com.river.engine.ast.operands.calculate.ParenthesisOperand;
import com.river.engine.ast.operands.calculate.arithmetic.*;
import com.river.engine.ast.operands.operand.ColumnOperand;
import com.river.engine.ast.operands.operand.NamingOperand;
import com.river.engine.ast.operands.operand.StringOperand;
import com.river.engine.ast.operands.primitive.BooleanOperand;
import com.river.engine.ast.operands.primitive.FloatOperand;
import com.river.engine.ast.operands.primitive.LongOperand;
import com.river.engine.context.RuleContext;
import com.river.engine.dsl.exception.UnSupportFunctionException;
import com.river.engine.grammar.RuleSQLParser;
import com.river.engine.grammar.RuleSQLVisitor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

/**
 * @author Juyony Chen
 * @version 1.0.0, 2020/9/15 17:50
 * @since 1.0.0, 2020/9/15 17:50
 **/
public abstract class AbstractRuleElementParser<P> extends AbstractStatementParser<P> implements RuleSQLVisitor<Object> {


    @Setter
    @Getter
    protected RuleContext ruleContext =new RuleContext();

    @Override
    public Object visitCalculateStatement(RuleSQLParser.CalculateStatementContext ctx) {
        visitMetrics(ctx.metrics());
        return true;
    }

    @Override
    public Object visitFilterConditions(RuleSQLParser.FilterConditionsContext ctx) {
        return visitAlertCondition(ctx.filterCondition());
    }

    @Override
    public Object visitBasicExpr(RuleSQLParser.BasicExprContext ctx) {
        return visitBasicBoolExpr(ctx.basicBoolExpr());
    }


    protected Object visitAlertCondition(RuleSQLParser.FilterConditionContext context) {
        if (context instanceof RuleSQLParser.LrExprContext) {
            return visitLrExpr((RuleSQLParser.LrExprContext) context);
        } else if (context instanceof RuleSQLParser.AndOprContext) {
            return visitAndOpr((RuleSQLParser.AndOprContext) context);
        } else if (context instanceof RuleSQLParser.OrOprContext) {
            return visitOrOpr((RuleSQLParser.OrOprContext) context);
        } else if (context instanceof RuleSQLParser.BasicExprContext) {
            return visitBasicBoolExpr(((RuleSQLParser.BasicExprContext) context).basicBoolExpr());
        }

        throw new UnSupportFunctionException("无法支持的逻辑組合操作: {}", context.getClass());
    }

    private Object visitBasicBoolExpr(RuleSQLParser.BasicBoolExprContext context) {
        if (context instanceof RuleSQLParser.CompareExprContext) {
            return visitCompareExpr((RuleSQLParser.CompareExprContext) context);
        } else if (context instanceof RuleSQLParser.InExprContext) {
            return visitInExpr((RuleSQLParser.InExprContext) context);
        }else if (context instanceof RuleSQLParser.LikeExprContext) {
            return visitLikeExpr((RuleSQLParser.LikeExprContext) context);
        }

        throw new UnSupportFunctionException("无法支持的逻辑运算操作: {}", context.getClass());
    }
    
    @Override
    public Object visitLrExpr(RuleSQLParser.LrExprContext ctx) {
        return new ParenthesisExpression((Expression) visitAlertCondition(ctx.filterCondition()));
    }

    @Override
    public Object visitAndOpr(RuleSQLParser.AndOprContext ctx) {

        final Expression left = (Expression) visitAlertCondition(ctx.left);
        final Expression right = (Expression) visitAlertCondition(ctx.right);

        return new AndExpression(left, right);
    }

    @Override
    public Object visitOrOpr(RuleSQLParser.OrOprContext ctx) {
        final Expression left = (Expression) visitAlertCondition(ctx.left);
        final Expression right = (Expression) visitAlertCondition(ctx.right);

        return new OrExpression(left, right);
    }

    @Override
    public Object visitCompareExpr(RuleSQLParser.CompareExprContext ctx) {
        final int type = ctx.option.getType();
        final Operand left = (Operand) visitMetricValue(ctx.left);
        final Operand right = (Operand) visitMetricValue(ctx.right);

        if (RuleSQLParser.EQ == type || RuleSQLParser.EQEQ == type) {
            return new EqualsExpression(left, right);
        } else if (RuleSQLParser.GT == type) {
            return new GreatThanExpression(left, right);
        } else if (RuleSQLParser.LT == type) {
            return new LessThanExpression(left, right);
        } else if (RuleSQLParser.GTEQ == type) {
            return new GreatEqualExpression(left, right);
        } else if (RuleSQLParser.LTEQ == type) {
            return new LessEqualExpression(left, right);
        } else if (RuleSQLParser.NEQ == type) {
            return new NotExpression(new EqualsExpression(left, right));
        }

        throw new UnSupportFunctionException("无法支持的比较运算操作: {}", ctx.option.getText());
    }



    @Override
    public Object visitLikeExpr(RuleSQLParser.LikeExprContext ctx) {
        final Operand left = (Operand) visitMetricValue(ctx.left);
        final String likeMessage =removeQuote(visitLikeMessage(ctx.right));

        // * is a special character for like calculation, like conditions should not contained non-like meaning of * character
        return new LikeExpression(left, new StringOperand(likeMessage.replaceAll("\\*", "\\.*")));
    }

    @Override
    public String visitLikeToken(RuleSQLParser.LikeTokenContext ctx) {
        return ctx.getText();
    }

    @Override
    public String visitLikeMessage(RuleSQLParser.LikeMessageContext ctx) {
        return ctx.getText();
    }


    @Override
    public Object visitInExpr(RuleSQLParser.InExprContext ctx) {
        final Boolean in = (Boolean) visitInToken(ctx.option);
        final Operand left = (Operand) visitMetricValue(ctx.left);
        final List<Operand> collections = (List<Operand>) visitCollection(ctx.right);
        final InExpression inExpression = new InExpression(left, collections);

        return in ? inExpression : new NotExpression(inExpression);
    }

    @Override
    public Object visitColumnValue(RuleSQLParser.ColumnValueContext context) {
        return visitIdentity(context.identity());
    }


    private Object visitIdentity(RuleSQLParser.IdentityContext context) {
        if (context instanceof RuleSQLParser.IdEleContext) {
            return visitIdEle((RuleSQLParser.IdEleContext) context);
        } else if (context instanceof RuleSQLParser.AliasEleContext) {
            return visitAliasEle((RuleSQLParser.AliasEleContext) context);
        } else if (context instanceof RuleSQLParser.IntEleContext) {
            return visitIntEle((RuleSQLParser.IntEleContext) context);
        } else if (context instanceof RuleSQLParser.FloatEleContext) {
            return visitFloatEle((RuleSQLParser.FloatEleContext) context);
        } else if (context instanceof RuleSQLParser.NegativeIntEleContext) {
            return visitNegativeIntEle((RuleSQLParser.NegativeIntEleContext) context);
        } else if (context instanceof RuleSQLParser.NegativeFloatELeContext) {
            return visitNegativeFloatELe((RuleSQLParser.NegativeFloatELeContext) context);
        } else if (context instanceof RuleSQLParser.BooleanEleContext) {
            return visitBooleanEle((RuleSQLParser.BooleanEleContext) context);
        } else if (context instanceof RuleSQLParser.StringEleContext) {
            return visitStringEle((RuleSQLParser.StringEleContext) context);
        }else if (context instanceof RuleSQLParser.AggFunctionContext) {
            return visitAggFunction((RuleSQLParser.AggFunctionContext) context);
        }

        throw new UnSupportFunctionException("无法支持的计算指标类型: {}", context.getClass());
    }

    @Override
    public Object visitAggFunction(RuleSQLParser.AggFunctionContext context){
        String functionName = context.ID().getText();

        List<Object> parameters = Lists.newArrayList();
        parameters.add(visitAggParameters(context.aggParameters()).toString());
        Object[] obj = parameters.toArray();
        return FunctionManager.createFunction(functionName, obj);

    }
//
//    @Override
//    public Object visitAggregationValue(RuleSQLParser.AggregationValueContext context) {
//        String functionName =context.ID().getText();
//
//        List<Object> parameters = Lists.newArrayList();
//
//        Operand metricValue = (Operand) visitMetricValue(context.metricValue());
//        parameters.add(metricValue);
//
//        List<Object> functionParameters = visitAggregateFunctionParameters(context.aggregateFunctionParameters());
//        parameters.addAll(functionParameters);
//        Object[] funcParams = parameters.toArray();
//
//        AbstractFunctionOperand functionOperand = FunctionManager.createFunction(functionName, funcParams);
//        return functionOperand;
//    }

    @Override
    public List<Object>  visitAggregateFunctionParameters(RuleSQLParser.AggregateFunctionParametersContext context) {
        if (context == null) {
            // Make sure we would have a default event queue length
            return singletonList(1);
        } else {
            List<Object> normalParameters = Lists.newArrayListWithExpectedSize(3);
            Token startOffset = context.startOffset;
            Token endOffset = context.endOffset;

            long startMinutes;
            long endMinutes;

            if (startOffset == null) {
                startMinutes = 1;
            } else {
                startMinutes = Long.parseLong(startOffset.getText());
            }
            normalParameters.add(startMinutes);

            if (endOffset != null) {
                endMinutes = Long.parseLong(endOffset.getText());
                normalParameters.add(endMinutes);
            }

            RuleSQLParser.FilterConditionContext conditions = context.filterCondition();
            if (conditions != null) {
                Expression filterFunc = (Expression) visitAlertCondition(conditions);
                normalParameters.add(filterFunc);
            }

            return normalParameters;
        }
    }

    @Override
    public List<Operand> visitMetrics(RuleSQLParser.MetricsContext ctx) {
        // List<NamingOperand> would be the return type
        return ctx.metric().stream()
                .map(this::visitMetric)
                .map(Operand.class::cast)
                .collect(toList());
    }

    @Override
    public Object visitInToken(RuleSQLParser.InTokenContext ctx) {
        return ctx.NOT() == null;
    }

    @Override
    public Object visitCollection(RuleSQLParser.CollectionContext ctx) {
        return ctx.identity().stream()
                .map(this::visitIdentity)
                .map(Operand.class::cast)
                .collect(toList());
    }

    private Object visitMetricValue(RuleSQLParser.MetricValueContext context) {
        if (context instanceof RuleSQLParser.LRValueContext) {
            return visitLRValue((RuleSQLParser.LRValueContext) context);
        } else if (context instanceof RuleSQLParser.MulValueContext) {
            return visitMulValue((RuleSQLParser.MulValueContext) context);
        } else if (context instanceof RuleSQLParser.AddValueContext) {
            return visitAddValue((RuleSQLParser.AddValueContext) context);
        } else if (context instanceof RuleSQLParser.ColumnValueContext) {
            return visitIdentity(((RuleSQLParser.ColumnValueContext) context).identity());
        }

        throw new UnSupportFunctionException("无法支持的计算指标类型: {}", context.getClass());
    }

    @Override
    public Object visitMetric(RuleSQLParser.MetricContext ctx) {
        Operand operand = (Operand) visitMetricValue(ctx.metricValue());
        if(operand instanceof ColumnOperand ){
            ruleContext.registerOperand(operand);
        }else{
            ruleContext.registerOperand(new NamingOperand(ctx.getChild(2).getText(), operand));
        }
        return operand;
    }

    @Override
    public Object visitAggParameters(RuleSQLParser.AggParametersContext ctx){
        return ctx.firstIndex.getText();
    }

    @Override
    public Object visitFromtype(RuleSQLParser.FromtypeContext ctx) {
        return null;
    }

    @Override
    public String visitSplitByExpr(RuleSQLParser.SplitByExprContext ctx) {
        return removeQuote(ctx.splitByValue().STRING().getText());
    }

    @Override
    public Object visitIdEle(RuleSQLParser.IdEleContext ctx) {
        String idText = ctx.getText();
        return createMetricOperand(idText);
    }

    private Operand createMetricOperand(String metricName) {
        Operand operand = new ColumnOperand(metricName);
        return operand;
    }

    @Override
    public Object visitIntEle(RuleSQLParser.IntEleContext ctx) {
        return new LongOperand(ctx.getText());
    }

    @Override
    public Object visitAliasEle(RuleSQLParser.AliasEleContext ctx) {
        return null;
    }

    @Override
    public Object visitFloatEle(RuleSQLParser.FloatEleContext ctx) {
        return new FloatOperand(ctx.getText());
    }

    @Override
    public Object visitNegativeIntEle(RuleSQLParser.NegativeIntEleContext ctx) {
        return new LongOperand(ctx.getText());
    }

    @Override
    public Object visitNegativeFloatELe(RuleSQLParser.NegativeFloatELeContext ctx) {
        return new FloatOperand(ctx.getText());
    }

    @Override
    public Object visitBooleanEle(RuleSQLParser.BooleanEleContext ctx) {
        return BooleanOperand.valueOf(ctx.getText());
    }

    @Override
    public Object visitStringEle(RuleSQLParser.StringEleContext ctx) {
        return new StringOperand(removeDoubleQuote(ctx.getText()));
    }

    private String removeDoubleQuote(String quotedText) {
        return quotedText.substring(quotedText.indexOf('"') + 1, quotedText.lastIndexOf('"'));
    }
    @Override
    public StringOperand visitSplitByValue(RuleSQLParser.SplitByValueContext ctx) {
        return new StringOperand(ctx.STRING().getText());
    }

    @Override
    public String visitSource(RuleSQLParser.SourceContext ctx) {
        return ctx.getText();
    }

    @Override
    public List<Object> visitDateParameters(RuleSQLParser.DateParametersContext ctx) {
        List<Object> parameters = new ArrayList<>();

        Token dateFormat = ctx.dateFormat;

        if(ctx.dateFormat != null){
            parameters.add(removeQuote(dateFormat.getText()));
        }

        return  parameters;
    }


    @Override
    public Object visitAddValue(RuleSQLParser.AddValueContext context) {
        int type = context.op.getType();

        Operand leftOperand = (Operand) visitMetricValue(context.left);
        Operand rightOperand = (Operand) visitMetricValue(context.right);

        if (type == RuleSQLParser.PLUS) {
            return new AddOperand(leftOperand, rightOperand);
        } else if (type == RuleSQLParser.SUB) {
            return new MinusOperand(leftOperand, rightOperand);
        }

        throw new IllegalArgumentException("无法支持的逻辑运算操作: "+context.op.getText());

    }


    @Override
    public Object visitMulValue(RuleSQLParser.MulValueContext context) {
        int type = context.op.getType();

        Operand leftOperand = (Operand) visitMetricValue(context.left);
        Operand rightOperand = (Operand) visitMetricValue(context.right);

        if (type == RuleSQLParser.STAR) {
            return new MultiplyOperand(leftOperand, rightOperand);
        } else if (type == RuleSQLParser.SLASH) {
            return new DivideOperand(leftOperand, rightOperand);
        } else if (type == RuleSQLParser.MOD) {
            return new ModuloOperand(leftOperand, rightOperand);
        }

        throw new IllegalArgumentException("无法支持的逻辑运算操作: "+context.op.getText());
    }


    @Override
    public Object visitLRValue(RuleSQLParser.LRValueContext context) {
        Operand operand = (Operand) visitMetricValue(context.metricValue());
        return new ParenthesisOperand(operand);
    }

    protected String removeQuote(String str) {
        final int lastIndex = str.length() - 1;
        if ((str.charAt(0) == '\'' && str.charAt(lastIndex) == '\'')
                || (str.charAt(0) == '"' && str.charAt(lastIndex) == '"')) {
            return str.substring(1, lastIndex);
        } else {
            return str;
        }
    }

    @Override
    public Object visit(ParseTree parseTree) {
        return null;
    }

    @Override
    public Object visitChildren(RuleNode ruleNode) {
        return null;
    }

    @Override
    public Object visitTerminal(TerminalNode terminalNode) {
        return null;
    }

    @Override
    public Object visitErrorNode(ErrorNode errorNode) {
        return null;
    }


}
