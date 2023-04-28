package org.example.automata.word;

import org.example.automata.State;
import org.example.lexer.TokenWithPosition;
import org.example.util.Util;
import org.example.automata.PreDefinedSequenceAutomata;


public class KeywordAutomata extends PreDefinedSequenceAutomata {

    public KeywordAutomata() {
        super(Util.keyWordTokens);
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
                if (Util.isTokenEnd(currentSymbol)) {
                    break;
                } else {
                    return processInvalidToken(line, position);
                }
            }
        }
        return new TokenWithPosition(state.getToken(), position);
    }

}
