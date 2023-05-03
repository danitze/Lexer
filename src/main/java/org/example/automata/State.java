package org.example.automata;

import org.example.token.BaseToken;
import org.example.token.InvalidToken;
import org.example.token.Token;

import java.util.HashMap;
import java.util.Map;

public class State {

    private BaseToken token;

    private final Map<Character, State> nextStates;

    public State() {
        token = new InvalidToken("Unknown issue");
        nextStates = new HashMap<>();
    }

    public void setToken(BaseToken token) {
        this.token = token;
    }

    public BaseToken getToken() {
        return token;
    }

    public Map<Character, State> getNextStates() {
        return nextStates;
    }
}
