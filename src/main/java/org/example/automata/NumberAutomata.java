package org.example.automata;

import org.example.State;
import org.example.Token;
import org.example.TokenWithPosition;
import org.example.Util;

public class NumberAutomata extends Automata {

    public NumberAutomata() {
        super();
        init();
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
                if (Util.isTokenEnd(currentSymbol)) {
                    break;
                } else {
                    return new TokenWithPosition(Token.INVALID, position);
                }
            }
        }
        return new TokenWithPosition(state.getToken(), position);
    }

    private void init() {
        State intState = new State();
        intState.setToken(Token.INT_VALUE);

        State dotState = new State();

        State floatState = new State();
        floatState.setToken(Token.FLOAT_VALUE);

        for (int i = 0; i < 9; ++i) {
            initialState.getNextStates().put(Character.forDigit(i, 10), intState);
            intState.getNextStates().put(Character.forDigit(i, 10), intState);
            dotState.getNextStates().put(Character.forDigit(i, 10), floatState);
            floatState.getNextStates().put(Character.forDigit(i, 10), floatState);
        }
        initialState.getNextStates().put('.', dotState);
        intState.getNextStates().put('.', dotState);
    }


}
