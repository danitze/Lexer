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
                    return processInvalidToken("Invalid identifier", line, position);
                }
                break;
            }
        }
        return new TokenWithPosition(state.getToken(), position);
    }

    private void init() {
        State underscoreState = new State();
        State identifierFirstSymbolState = new State(Token.IDENTIFIER);
        State identifierSymbolState = new State(Token.IDENTIFIER);
        State intIdentifierState = new State(Token.INTEGER_IDENTIFIER);
        State longIdentifierState = new State(Token.LONG_IDENTIFIER);
        State floatIdentifierState = new State(Token.FLOAT_IDENTIFIER);
        State stringIdentifierState = new State(Token.STRING_IDENTIFIER);

        for(int i = 0; i < 26; ++i) {
            initialState.getNextStates().put((char)('a' + i), identifierFirstSymbolState);
            initialState.getNextStates().put((char)('A' + i), identifierFirstSymbolState);

            identifierFirstSymbolState.getNextStates().put((char)('a' + i), identifierSymbolState);
            identifierFirstSymbolState.getNextStates().put((char)('A' + i), identifierSymbolState);

            identifierSymbolState.getNextStates().put((char)('a' + i), identifierSymbolState);
            identifierSymbolState.getNextStates().put((char)('A' + i), identifierSymbolState);

            underscoreState.getNextStates().put((char)('a' + i), identifierSymbolState);
            underscoreState.getNextStates().put((char)('A' + i), identifierSymbolState);
        }

        initialState.getNextStates().put('_', underscoreState);
        underscoreState.getNextStates().put('_', identifierSymbolState);
        identifierFirstSymbolState.getNextStates().put('_', identifierSymbolState);
        identifierSymbolState.getNextStates().put('_', identifierSymbolState);

        for (int i = 0; i < 10; ++i) {
            underscoreState.getNextStates().put(Character.forDigit(i, 10), identifierSymbolState);
            identifierFirstSymbolState.getNextStates().put(Character.forDigit(i, 10), identifierSymbolState);
            identifierSymbolState.getNextStates().put(Character.forDigit(i, 10), identifierSymbolState);
        }

        identifierFirstSymbolState.getNextStates().put('%', intIdentifierState);
        identifierSymbolState.getNextStates().put('%', intIdentifierState);

        identifierFirstSymbolState.getNextStates().put('&', longIdentifierState);
        identifierSymbolState.getNextStates().put('&', longIdentifierState);

        identifierFirstSymbolState.getNextStates().put('#', floatIdentifierState);
        identifierSymbolState.getNextStates().put('#', floatIdentifierState);

        identifierFirstSymbolState.getNextStates().put('$', stringIdentifierState);
        identifierSymbolState.getNextStates().put('$', stringIdentifierState);
    }
}
