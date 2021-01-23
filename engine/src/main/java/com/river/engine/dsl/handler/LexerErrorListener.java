
package com.river.engine.dsl.handler;

import com.river.engine.dsl.exception.LexicalErrorException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

/**
 * The lexer token error listener
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LexerErrorListener extends BaseErrorListener {

    private static final LexerErrorListener INSTANCE = new LexerErrorListener();

    // offendingSymbol is null when lexer error
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
                            int charPositionInLine, String msg, RecognitionException e) {

        String position = "line " + line + ", pos " + charPositionInLine;
        String charText = "";
        String hint = "";
        if (recognizer instanceof Lexer) {
            Lexer lexer = (Lexer) recognizer;
            String fullText = lexer.getInputStream().toString();
            charText = String.valueOf(fullText.charAt(lexer.getCharIndex()));
            hint = ErrorCommon.underlineError(fullText, charText, line, charPositionInLine);
        }
        throw new LexicalErrorException(position + " near " + charText + " : " + msg + "\n" + hint, e);
    }

    public static LexerErrorListener getInstance() {
        return INSTANCE;
    }




}
