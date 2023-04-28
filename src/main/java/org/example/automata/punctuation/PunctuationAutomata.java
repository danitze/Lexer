package org.example.automata.punctuation;

import org.example.util.Util;
import org.example.automata.PreDefinedSequenceAutomata;

public class PunctuationAutomata extends PreDefinedSequenceAutomata {

    public PunctuationAutomata() {
        super(Util.punctuationTokens);
    }
}
