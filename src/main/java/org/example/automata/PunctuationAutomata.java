package org.example.automata;

import org.example.Util;

public class PunctuationAutomata extends PreDefinedSequenceAutomata {

    public PunctuationAutomata() {
        super(Util.punctuationTokens);
    }
}
