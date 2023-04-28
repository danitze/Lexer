package org.example.automata;

import org.example.token.Token;
import org.example.lexer.TokenWithPosition;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class PreDefinedSequenceAutomata extends Automata {

    public PreDefinedSequenceAutomata(List<Token> tokens) {
        super();
        init(initialState, tokens, 0);
    }

    @Override
    public TokenWithPosition check(String line, int position) {
        State state = initialState;
        while (true) {
            if (line.length() <= position) {
                break;
            }
            char currentSymbol = line.charAt(position);
            if (state.getNextStates().containsKey(currentSymbol)) {
                state = state.getNextStates().get(currentSymbol);
                ++position;
            } else {
                break;
            }
        }
        return new TokenWithPosition(state.getToken(), position);
    }

    private void init(State state, List<Token> tokens, int depth) {
        Map<Character, List<Token>> followingTokens = new HashMap<>();
        for (Token token : tokens) {
            if (token.getValue().length() > depth) {
                char symbol = token.getValue().charAt(depth);
                if (!followingTokens.containsKey(symbol)) {
                    followingTokens.put(symbol, new LinkedList<>());
                }
                followingTokens.get(symbol).add(token);
            }
        }
        for (Map.Entry<Character, List<Token>> symbolWithTokens : followingTokens.entrySet()) {
            State nextState = new State();
            state.getNextStates().put(symbolWithTokens.getKey(), nextState);
            for (Token symbolToken : symbolWithTokens.getValue()) {
                if (symbolToken.getValue().length() == depth + 1) {
                    nextState.setToken(symbolToken);
                }
            }
            init(nextState, followingTokens.get(symbolWithTokens.getKey()), depth + 1);
        }
    }
}
