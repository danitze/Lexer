package org.example.automata.whitespace;

import org.example.util.Util;
import org.example.automata.PreDefinedSequenceAutomata;

public class WhitespaceAutomata extends PreDefinedSequenceAutomata {

    public WhitespaceAutomata() {
        super(Util.whitespaceTokens);
    }
}
