package com.river.engine.dsl.parser;

import com.river.common.dto.RuleDTO;
import com.river.engine.dsl.handler.LexerErrorListener;
import com.river.engine.dsl.handler.SyntaxErrorListener;
import com.river.engine.dsl.handler.ThrowingExceptionErrorStrategy;
import com.river.engine.grammar.RuleSQLLexer;
import com.river.engine.grammar.RuleSQLParser;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.List;

/**
 * @author Juyony Chen
 * @version 1.0.0, 2020/9/14 19:00
 * @since 1.0.0, 2020/9/14 19:00
 **/
@Slf4j
public abstract class AbstractStatementParser<P> {

    public abstract P parser(String sql);

//    public abstract List<P> parser(RuleDTO ruleDTO);

    public static RuleSQLParser parseContext(String sql) {
        try {
            CharStream input = CharStreams.fromString(sql);

            RuleSQLLexer lexer = new RuleSQLLexer(input);
            lexer.removeErrorListeners();
            lexer.addErrorListener(LexerErrorListener.getInstance());

            CommonTokenStream token = new CommonTokenStream(lexer);

            RuleSQLParser parser = new RuleSQLParser(token);
            // Parser error handler
            parser.setErrorHandler(ThrowingExceptionErrorStrategy.getInstance());
            parser.removeErrorListeners();
            parser.addErrorListener(SyntaxErrorListener.getInstance());

            return parser;
        } catch (IllegalArgumentException e) {
            log.error("Parser The SQL error:", e);
            return null;
        }

    }


}
