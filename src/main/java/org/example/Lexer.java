package org.example;

import org.example.automata.*;

import java.util.LinkedList;
import java.util.List;

public class Lexer {

    private int column = 0;

    private DateAutomata dateAutomata = new DateAutomata();
    private OperatorAutomata operatorAutomata = new OperatorAutomata();
    private NumberAutomata numberAutomata = new NumberAutomata();
    private PunctuationAutomata punctuationAutomata = new PunctuationAutomata();
    private LineContinuationAutomata lineContinuationAutomata = new LineContinuationAutomata();
    private WhitespaceAutomata whitespaceAutomata = new WhitespaceAutomata();
    private KeywordAutomata keywordAutomata = new KeywordAutomata();
    private IdentifierAutomata identifierAutomata = new IdentifierAutomata();
    private CommentAutomata commentAutomata = new CommentAutomata();
    private StringAutomata stringAutomata = new StringAutomata();

    List<TokenWithPosition> tokens = new LinkedList<>();

    public void processLine(String line) {
        while (column < line.length()) {
            TokenWithPosition tokenWithPosition;
            char symbol = line.charAt(column);
            if (Util.isOperator(symbol)) {
                if (Util.isPoundSign(symbol)) {
                    tokenWithPosition = dateAutomata.check(line, column);
                    if (tokenWithPosition.token() != Token.INVALID) {
                        column = tokenWithPosition.position();
                        tokens.add(tokenWithPosition);
                        continue;
                    }
                }
                tokenWithPosition = operatorAutomata.check(line, column);
                column = tokenWithPosition.position();
                tokens.add(tokenWithPosition);
                continue;
            }
            if (Util.isPunctuation(symbol)) {
                if (Util.isDot(symbol)) {
                    tokenWithPosition = numberAutomata.check(line, column);
                    if (tokenWithPosition.token() != Token.INVALID) {
                        column = tokenWithPosition.position();
                        tokens.add(tokenWithPosition);
                        continue;
                    }
                }
                tokenWithPosition = punctuationAutomata.check(line, column);
                column = tokenWithPosition.position();
                tokens.add(tokenWithPosition);
                continue;
            }
            if (Util.isWhiteSpace(symbol)) {
                if (Util.isSpace(symbol)) {
                    tokenWithPosition = lineContinuationAutomata.check(line, column);
                    if (tokenWithPosition.token() != Token.INVALID) {
                        column = tokenWithPosition.position();
                        tokens.add(tokenWithPosition);
                        continue;
                    }
                }
                tokenWithPosition = whitespaceAutomata.check(line, column);
                column = tokenWithPosition.position();
                tokens.add(tokenWithPosition);
                continue;
            }
            if (Util.isDigit(symbol)) {
                tokenWithPosition = numberAutomata.check(line, column);
                column = tokenWithPosition.position();
                tokens.add(tokenWithPosition);
                continue;
            }
            if (Util.isIdentifierBegin(symbol)) {
                tokenWithPosition = keywordAutomata.check(line, column);
                if (tokenWithPosition.token() != Token.INVALID) {
                    column = tokenWithPosition.position();
                    tokens.add(tokenWithPosition);
                    continue;
                }
                tokenWithPosition = identifierAutomata.check(line, column);
                column = tokenWithPosition.position();
                tokens.add(tokenWithPosition);
                continue;
            }
            if (Util.isCommentStart(symbol)) {
                tokenWithPosition = commentAutomata.check(line, column);
                column = tokenWithPosition.position();
                tokens.add(tokenWithPosition);
                continue;
            }
            if (Util.isDoubleQuotes(symbol)) {
                tokenWithPosition = stringAutomata.check(line, column);
                column = tokenWithPosition.position();
                tokens.add(tokenWithPosition);
                continue;
            }
        }
    }

    public List<TokenWithPosition> getTokens() {
        return tokens;
    }
}
