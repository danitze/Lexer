package org.example.automata.comment;

import org.example.automata.State;
import org.example.token.Token;
import org.example.lexer.TokenWithPosition;
import org.example.automata.Automata;

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
                currentSymbol = (char) -1;
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

        //-1 is any symbol
        commentState.getNextStates().put((char) -1, commentState);
    }
}
