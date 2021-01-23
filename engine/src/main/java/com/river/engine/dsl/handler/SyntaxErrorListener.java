/*
 *
 *
 *
 *
 *
 */
package com.river.engine.dsl.handler;


import com.river.engine.dsl.exception.SyntaxErrorException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.*;

/**
 * The listener for dsl syntax error
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SyntaxErrorListener extends BaseErrorListener {

    private static final SyntaxErrorListener INSTANCE = new SyntaxErrorListener();

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
                            int charPositionInLine, String msg, RecognitionException e) {
        String position = "line " + line + ", pos " + charPositionInLine;
        String tokenName = "";
        String hint = "";

        if (offendingSymbol instanceof Token && recognizer instanceof Parser) {
            Token token = (Token) offendingSymbol;
            tokenName = token.getText();
            String fullText = ((Parser) recognizer).getTokenStream().getTokenSource().getInputStream().toString();
            hint = ErrorCommon.underlineError(fullText, tokenName, line, charPositionInLine);
        }
        throw new SyntaxErrorException(position + " near " + tokenName + " : " + msg + "\n" + hint, e);
    }

    public static SyntaxErrorListener getInstance() {
        return INSTANCE;
    }
}
