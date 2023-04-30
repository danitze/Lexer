package org.example.lexer;

import org.example.util.Util;
import org.example.automata.comment.CommentAutomata;
import org.example.automata.date.DateAutomata;
import org.example.automata.line_continuation.LineContinuationAutomata;
import org.example.automata.number.NumberAutomata;
import org.example.automata.operator.OperatorAutomata;
import org.example.automata.punctuation.PunctuationAutomata;
import org.example.automata.string.StringAutomata;
import org.example.automata.whitespace.WhitespaceAutomata;
import org.example.automata.word.IdentifierAutomata;
import org.example.automata.word.KeywordAutomata;
import org.example.token.InvalidToken;
import org.example.token.Token;
import org.example.token.ValidToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Lexer {

    private int row;
    private int column;

    private String line = "";

    private final DateAutomata dateAutomata = new DateAutomata();
    private final OperatorAutomata operatorAutomata = new OperatorAutomata();
    private final NumberAutomata numberAutomata = new NumberAutomata();
    private final PunctuationAutomata punctuationAutomata = new PunctuationAutomata();
    private final LineContinuationAutomata lineContinuationAutomata = new LineContinuationAutomata();
    private final WhitespaceAutomata whitespaceAutomata = new WhitespaceAutomata();
    private final KeywordAutomata keywordAutomata = new KeywordAutomata();
    private final IdentifierAutomata identifierAutomata = new IdentifierAutomata();
    private final CommentAutomata commentAutomata = new CommentAutomata();
    private final StringAutomata stringAutomata = new StringAutomata();

    private final List<String> symbolTable = new ArrayList<>();

    private final List<ValidToken> validTokens = new ArrayList<>();

    private final List<InvalidToken> invalidTokens = new ArrayList<>();

    public void processFile(String filePath) throws IOException {
        row = 0;
        column = 0;

        Path path = Paths.get(filePath);
        BufferedReader reader = Files.newBufferedReader(path);
        String line;
        while ((line = reader.readLine()) != null) {
            this.line = line;
            processLine();
            ++row;
            column = 0;
        }
        reader.close();
    }

    public void print() {
        for (int i = 0; i < validTokens.size(); ++i) {
            ValidToken validToken = validTokens.get(i);
            String symbol = symbolTable.get(i);
            System.out.println("Token: " + validToken.getToken() + " | symbol table index: " + i + " | row: " + validToken.getRow() + " | column: " + validToken.getColumn() + " | symbol: |" + symbol + "|");
        }
        if (!invalidTokens.isEmpty()) {
            System.out.println("Errors");
        }
        for (InvalidToken invalidToken: invalidTokens) {
            System.out.println("Invalid token | row: " + invalidToken.row() + " | column: " + invalidToken.column() + " | sequence: |" + invalidToken.sequence() + "|");
        }
    }

    private void processLine() {
        while (column < line.length()) {
            TokenWithPosition tokenWithPosition;
            char symbol = line.charAt(column);
            if (Util.isOperator(symbol)) {
                tokenWithPosition = operatorAutomata.check(line, column);
                registerToken(tokenWithPosition);
                continue;
            }
            if (Util.isPunctuation(symbol)) {
                if (Util.isDot(symbol)) {
                    tokenWithPosition = numberAutomata.check(line, column);
                    if (tokenWithPosition.token() != Token.INVALID) {
                        registerToken(tokenWithPosition);
                        continue;
                    }
                } else if (Util.isPoundSign(symbol)) {
                    tokenWithPosition = dateAutomata.check(line, column);
                    if (tokenWithPosition.token() != Token.INVALID) {
                        registerToken(tokenWithPosition);
                        continue;
                    }
                }
                tokenWithPosition = punctuationAutomata.check(line, column);
                registerToken(tokenWithPosition);
                continue;
            }
            if (Util.isWhiteSpace(symbol)) {
                if (Util.isSpace(symbol)) {
                    tokenWithPosition = lineContinuationAutomata.check(line, column);
                    if (tokenWithPosition.token() != Token.INVALID) {
                        registerToken(tokenWithPosition);
                        continue;
                    }
                }
                tokenWithPosition = whitespaceAutomata.check(line, column);
                registerToken(tokenWithPosition);
                continue;
            }
            if (Util.isDigit(symbol)) {
                tokenWithPosition = numberAutomata.check(line, column);
                registerToken(tokenWithPosition);
                continue;
            }
            if (Util.isIdentifierBegin(symbol)) {
                tokenWithPosition = keywordAutomata.check(line, column);
                if (tokenWithPosition.token() != Token.INVALID) {
                    registerToken(tokenWithPosition);
                    continue;
                }
                tokenWithPosition = identifierAutomata.check(line, column);
                registerToken(tokenWithPosition);
                continue;
            }
            if (Util.isCommentStart(symbol)) {
                tokenWithPosition = commentAutomata.check(line, column);
                registerToken(tokenWithPosition);
                continue;
            }
            if (Util.isDoubleQuotes(symbol)) {
                tokenWithPosition = stringAutomata.check(line, column);
                registerToken(tokenWithPosition);
            }
        }
    }

    private void registerToken(TokenWithPosition tokenWithPosition) {
        Token token = tokenWithPosition.token();
        int position = tokenWithPosition.position();
        if (token == Token.INVALID) {
            InvalidToken invalidToken = new InvalidToken(line.substring(column, position), row + 1, column + 1);
            invalidTokens.add(invalidToken);
        } else {
            symbolTable.add(line.substring(column, position));

            ValidToken validToken = new ValidToken(token, row + 1, column + 1);
            validTokens.add(validToken);
        }
        column = tokenWithPosition.position();
    }
}
