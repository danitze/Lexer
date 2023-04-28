package org.example.automata;

import org.example.State;
import org.example.Token;
import org.example.TokenWithPosition;

public class CommentAutomata extends Automata {

    public CommentAutomata() {
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
            if (state != initialState) {
                currentSymbol = '*';
            }
            if (state.getNextStates().containsKey(currentSymbol)) {
                state = state.getNextStates().get(currentSymbol);
                ++position;
            } else {
                break;
            }
        }
        return new TokenWithPosition(state.getToken(), position);
    }

    private void init() {
        State commentState = new State();
        commentState.setToken(Token.COMMENTARY);
        initialState.getNextStates().put('\'', commentState);

        //* is any symbol
        commentState.getNextStates().put('*', commentState);
    }
}
