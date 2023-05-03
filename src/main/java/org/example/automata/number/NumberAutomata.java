package org.example.automata.number;

import org.example.automata.State;
import org.example.token.Token;
import org.example.lexer.TokenWithPosition;
import org.example.util.Util;
import org.example.automata.Automata;

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
                    return processInvalidToken("Invalid identifier", line, position);
                }
            }
        }
        return new TokenWithPosition(state.getToken(), position);
    }

    private void init() {
        State intState = new State(Token.INT_VALUE);
        State dotState = new State();
        State floatState = new State(Token.FLOAT_VALUE);
        State explicitIntState = new State(Token.INT_VALUE);
        State explicitFloatState = new State(Token.FLOAT_VALUE);

        for (int i = 0; i < 10; ++i) {
            initialState.getNextStates().put(Character.forDigit(i, 10), intState);
            intState.getNextStates().put(Character.forDigit(i, 10), intState);
            dotState.getNextStates().put(Character.forDigit(i, 10), floatState);
            floatState.getNextStates().put(Character.forDigit(i, 10), floatState);
        }
        initialState.getNextStates().put('.', dotState);

        intState.getNextStates().put('.', dotState);
        intState.getNextStates().put('%', explicitIntState);
        intState.getNextStates().put('#', explicitFloatState);

        floatState.getNextStates().put('#', explicitFloatState);
    }


}
