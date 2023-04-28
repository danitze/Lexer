package org.example.automata.word;

import org.example.automata.State;
import org.example.token.Token;
import org.example.lexer.TokenWithPosition;
import org.example.util.Util;
import org.example.automata.Automata;

public class IdentifierAutomata extends Automata {

    public IdentifierAutomata() {
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
            if (state.getNextStates().containsKey(currentSymbol)) {
                state = state.getNextStates().get(currentSymbol);
                ++position;
            } else {
                if (!Util.isTokenEnd(currentSymbol)) {
                    return processInvalidToken(line, position);
                }
                break;
            }
        }
        return new TokenWithPosition(state.getToken(), position);
    }

    private void init() {
        State identifierFirstSymbolState = new State();
        identifierFirstSymbolState.setToken(Token.IDENTIFIER);

        State identifierSymbolState = new State();
        identifierSymbolState.setToken(Token.IDENTIFIER);

        for(int i = 0; i < 26; ++i) {
            initialState.getNextStates().put((char)('a' + i), identifierFirstSymbolState);
            initialState.getNextStates().put((char)('A' + i), identifierFirstSymbolState);

            identifierFirstSymbolState.getNextStates().put((char)('a' + i), identifierSymbolState);
            identifierFirstSymbolState.getNextStates().put((char)('A' + i), identifierSymbolState);

            identifierSymbolState.getNextStates().put((char)('a' + i), identifierSymbolState);
            identifierSymbolState.getNextStates().put((char)('A' + i), identifierSymbolState);
        }

        initialState.getNextStates().put('_', identifierFirstSymbolState);
        identifierFirstSymbolState.getNextStates().put('_', identifierSymbolState);
        identifierSymbolState.getNextStates().put('_', identifierSymbolState);

        for (int i = 0; i < 10; ++i) {
            identifierFirstSymbolState.getNextStates().put(Character.forDigit(i, 10), identifierSymbolState);
            identifierSymbolState.getNextStates().put(Character.forDigit(i, 10), identifierSymbolState);
        }
    }
}
