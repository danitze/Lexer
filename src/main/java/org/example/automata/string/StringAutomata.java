package org.example.automata.string;

import org.example.automata.State;
import org.example.token.Token;
import org.example.lexer.TokenWithPosition;
import org.example.util.Util;
import org.example.automata.Automata;

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
            char oldSymbol = currentSymbol;
            if (currentSymbol != '\"') {
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
        State openQuotesState = new State();
        State charState = new State();
        State stringState = new State();

        State closedCharState = new State();
        closedCharState.setToken(Token.CHAR_VALUE);

        State closedStringState = new State();
        closedStringState.setToken(Token.STRING_VALUE);

        initialState.getNextStates().put('\"', openQuotesState);

        //We use -1 to denote any symbol which is not "
        openQuotesState.getNextStates().put((char) -1, charState);
        openQuotesState.getNextStates().put('\"', closedStringState);

        charState.getNextStates().put((char) -1, stringState);

        stringState.getNextStates().put((char) -1, stringState);

        charState.getNextStates().put('\"', closedCharState);
        stringState.getNextStates().put('\"', closedStringState);
    }
}
