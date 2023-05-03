package org.example.automata.line_continuation;

import org.example.automata.State;
import org.example.token.InvalidToken;
import org.example.token.Token;
import org.example.lexer.TokenWithPosition;
import org.example.automata.Automata;

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
                    return new TokenWithPosition(new InvalidToken(), position);
                }
            }
            char currentSymbol = line.charAt(position);
            if (state.getNextStates().containsKey(currentSymbol)) {
                state = state.getNextStates().get(currentSymbol);
                ++position;
            } else {
                return new TokenWithPosition(new InvalidToken(), position);
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
