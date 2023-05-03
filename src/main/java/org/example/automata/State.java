package org.example.automata;

import org.example.token.BaseToken;
import org.example.token.InvalidToken;

import java.util.HashMap;
import java.util.Map;

public class State {

    private BaseToken token;

    private final Map<Character, State> nextStates;

    public State(BaseToken token) {
        this.token = token;
        this.nextStates = new HashMap<>();
    }

    public State() {
        this(new InvalidToken("Unknown issue"));
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
