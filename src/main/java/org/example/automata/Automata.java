package org.example.automata;

import org.example.token.Token;
import org.example.lexer.TokenWithPosition;
import org.example.util.Util;

public abstract class Automata {

    protected final State initialState;

    public Automata() {
        initialState = new State();
    }

    public abstract TokenWithPosition check(String line, int position);

    protected TokenWithPosition processInvalidToken(String line, int position) {
        while (position < line.length() && !Util.isTokenEnd(line.charAt(position))) {
            ++position;
        }
        return new TokenWithPosition(Token.INVALID, position);
    }
}
