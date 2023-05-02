package org.example.automata.string;

import org.example.automata.State;
import org.example.token.Token;
import org.example.lexer.TokenWithPosition;
import org.example.util.Util;
import org.example.automata.Automata;

public class StringAutomata extends Automata {

    private final State openQuotesState = new State();
    private final State charState = new State();
    private final State stringState = new State();
    private final State closedCharState = new State();
    private final State castedCharState = new State();
    private final State closedStringState = new State();

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
            if ((state == openQuotesState || state == charState || state == stringState) && currentSymbol != '\"') {
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
        closedCharState.setToken(Token.STRING_VALUE);
        castedCharState.setToken(Token.CHAR_VALUE);
        closedStringState.setToken(Token.STRING_VALUE);

        initialState.getNextStates().put('\"', openQuotesState);

        //We use -1 to denote any symbol which is not "
        openQuotesState.getNextStates().put((char) -1, charState);
        openQuotesState.getNextStates().put('\"', closedStringState);

        charState.getNextStates().put((char) -1, stringState);

        stringState.getNextStates().put((char) -1, stringState);

        charState.getNextStates().put('\"', closedCharState);
        stringState.getNextStates().put('\"', closedStringState);

        closedCharState.getNextStates().put('c', castedCharState);
    }
}
