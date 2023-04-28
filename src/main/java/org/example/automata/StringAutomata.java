package org.example.automata;

import org.example.State;
import org.example.Token;
import org.example.TokenWithPosition;

public class StringAutomata extends Automata {

    public StringAutomata() {
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
            if (currentSymbol != '\"') {
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
        State openQuotesState = new State();
        State charState = new State();
        State stringState = new State();

        State closedCharState = new State();
        closedCharState.setToken(Token.CHAR_VALUE);

        State closedStringState = new State();
        closedStringState.setToken(Token.STRING_VALUE);

        initialState.getNextStates().put('\"', openQuotesState);

        //We use * to denote any symbol which is not "
        openQuotesState.getNextStates().put('*', charState);
        openQuotesState.getNextStates().put('\"', closedStringState);

        charState.getNextStates().put('*', stringState);

        stringState.getNextStates().put('*', stringState);

        charState.getNextStates().put('\"', closedCharState);
        stringState.getNextStates().put('\"', closedStringState);
    }
}
