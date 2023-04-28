package org.example.token;

import org.example.token.Token;

public class ValidToken {

    private final Token token;

    private final int row;

    private final int column;

    public ValidToken(Token token, int row, int column) {
        this.token = token;
        this.row = row;
        this.column = column;
    }

    public Token getToken() {
        return token;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
