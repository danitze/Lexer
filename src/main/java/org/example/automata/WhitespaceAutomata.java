package org.example.automata;

import org.example.Util;

public class WhitespaceAutomata extends PreDefinedSequenceAutomata {

    public WhitespaceAutomata() {
        super(Util.whitespaceTokens);
    }
}
