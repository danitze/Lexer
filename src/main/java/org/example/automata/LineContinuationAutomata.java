package org.example.automata;

import org.example.State;
import org.example.Token;
import org.example.TokenWithPosition;

public class LineContinuationAutomata extends Automata {

    public LineContinuationAutomata() {
        super();
        init();
    }

    @Override
    public TokenWithPosition check(String line, int position) {
        State state = initialState;
        while (true) {
            if (line.length() <= position) {
                if (state.getToken() == Token.LINE_CONTINUATION) {
                    return new TokenWithPosition(state.getToken(), position);
                } else {
                    return new TokenWithPosition(Token.INVALID, position);
                }
            }
            char currentSymbol = line.charAt(position);
            if (state.getNextStates().containsKey(currentSymbol)) {
                state = state.getNextStates().get(currentSymbol);
                ++position;
            } else {
                return new TokenWithPosition(Token.INVALID, position);
            }
        }
    }

    private void init() {
        State spaceState = new State();
        State lineContinuationState = new State();
        lineContinuationState.setToken(Token.LINE_CONTINUATION);

        initialState.getNextStates().put(' ', spaceState);
        spaceState.getNextStates().put('_', lineContinuationState);
    }
}
