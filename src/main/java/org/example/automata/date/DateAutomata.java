package org.example.automata.date;

import org.example.automata.State;
import org.example.token.Token;
import org.example.lexer.TokenWithPosition;
import org.example.util.Util;
import org.example.automata.Automata;

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
            char oldSymbol = currentSymbol;

            if (currentSymbol != '\"' && currentSymbol != '\'' && currentSymbol != '#') {
                currentSymbol = (char) -1;
            }

            if (state.getNextStates().containsKey(currentSymbol)) {
                state = state.getNextStates().get(currentSymbol);
                ++position;
            } else {
                currentSymbol = oldSymbol;
                if (Util.isTokenEnd(currentSymbol)) {
                    break;
                } else {
                    return processInvalidToken(line, position);
                }
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

        //We use -1 to denote any symbol which is not #, ' and "
        openHashState.getNextStates().put((char) -1, dateSymbolState);

        dateSymbolState.getNextStates().put((char) -1, dateSymbolState);
        dateSymbolState.getNextStates().put('#', closeHashState);
    }
}
