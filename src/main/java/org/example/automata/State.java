package org.example.automata;

import org.example.token.Token;

import java.util.HashMap;
import java.util.Map;

public class State {

    private Token token;

    private final Map<Character, State> nextStates;

    public State() {
        token = Token.INVALID;
        nextStates = new HashMap<>();
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return token;
    }

    public Map<Character, State> getNextStates() {
        return nextStates;
    }
}
