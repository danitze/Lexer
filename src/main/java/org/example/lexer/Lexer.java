package org.example.lexer;

import org.example.token.*;
import org.example.token.result_token.InvalidResultToken;
import org.example.token.result_token.ValidResultToken;
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

    private final List<ValidResultToken> validResultTokens = new ArrayList<>();

    private final List<InvalidResultToken> invalidResultTokens = new ArrayList<>();

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
        System.out.println("\nValid tokens");
        for (int i = 0; i < validResultTokens.size(); ++i) {
            ValidResultToken validResultToken = validResultTokens.get(i);
            String symbol = symbolTable.get(i);
            System.out.println("Token: " + validResultToken.getToken() + " | symbol table index: " + i + " | row: " + validResultToken.getRow() + " | column: " + validResultToken.getColumn() + " | symbol: |" + symbol + "|");
        }
        if (!invalidResultTokens.isEmpty()) {
            System.out.println("\nErrors");
        }
        for (InvalidResultToken invalidResultToken : invalidResultTokens) {
            String description = invalidResultToken.invalidToken().getDescription();
            System.out.println(description + " | row: " + invalidResultToken.row() + " | column: " + invalidResultToken.column() + " | sequence: |" + invalidResultToken.sequence() + "|");
        }
        System.out.println("\nSymbol table");
        for (int i = 0; i < symbolTable.size(); ++i) {
            System.out.println(i + 1 + ") |" + symbolTable.get(i) + "|");
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
            if (Util.isPoundSign(symbol)) {
                tokenWithPosition = dateAutomata.check(line, column);
                registerToken(tokenWithPosition);
                continue;
            }
            if (Util.isPunctuation(symbol)) {
                if (Util.isDot(symbol)) {
                    tokenWithPosition = numberAutomata.check(line, column);
                    if (!(tokenWithPosition.token() instanceof InvalidToken)) {
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
                    if (!(tokenWithPosition.token() instanceof InvalidToken)) {
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
                if (!(tokenWithPosition.token() instanceof InvalidToken)) {
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
        BaseToken token = tokenWithPosition.token();
        int position = tokenWithPosition.position();
        if (token instanceof InvalidToken) {
            InvalidResultToken invalidResultToken = new InvalidResultToken((InvalidToken) token, line.substring(column, position), row + 1, column + 1);
            invalidResultTokens.add(invalidResultToken);
        } else {
            symbolTable.add(line.substring(column, position));

            ValidResultToken validResultToken = new ValidResultToken((Token) token, row + 1, column + 1);
            validResultTokens.add(validResultToken);
        }
        column = tokenWithPosition.position();
    }
}
