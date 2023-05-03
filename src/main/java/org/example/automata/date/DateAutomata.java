package org.example.automata.date;

import org.example.automata.State;
import org.example.token.InvalidToken;
import org.example.token.Token;
import org.example.lexer.TokenWithPosition;
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

            if (currentSymbol != '\"' && currentSymbol != '\'' && currentSymbol != '#') {
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
        InvalidToken notClosedDateToken = new InvalidToken("Not closed date");
        InvalidToken emptyDateToken = new InvalidToken("Empty date");

        State openHashState = new State(notClosedDateToken);
        State dateSymbolState = new State(notClosedDateToken);
        State emptyCloseHashState = new State(emptyDateToken);
        State closeHashState = new State(Token.DATE_VALUE);

        initialState.getNextStates().put('#', openHashState);

        openHashState.getNextStates().put('#', emptyCloseHashState);
        //We use -1 to denote any symbol which is not #, ' and "
        openHashState.getNextStates().put((char) -1, dateSymbolState);

        dateSymbolState.getNextStates().put((char) -1, dateSymbolState);
        dateSymbolState.getNextStates().put('#', closeHashState);
    }
}
