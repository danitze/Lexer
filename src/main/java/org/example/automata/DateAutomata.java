package org.example.automata;

import org.example.State;
import org.example.Token;
import org.example.TokenWithPosition;

public class DateAutomata extends Automata {

    public DateAutomata() {
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
            if (currentSymbol != '\"' && currentSymbol != '\'' && currentSymbol != '#') {
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
        State openHashState = new State();
        State dateSymbolState = new State();
        State closeHashState = new State();
        closeHashState.setToken(Token.DATE_VALUE);

        initialState.getNextStates().put('#', openHashState);

        //We use * to denote any symbol which is not #, ' and "
        openHashState.getNextStates().put('*', dateSymbolState);

        dateSymbolState.getNextStates().put('#', closeHashState);
    }
}
