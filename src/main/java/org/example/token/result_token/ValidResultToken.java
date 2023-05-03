package org.example.token.result_token;

import org.example.token.Token;

public class ValidResultToken {

    private final Token token;

    private final int row;

    private final int column;

    public ValidResultToken(Token token, int row, int column) {
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
