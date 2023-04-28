package org.example.automata;

import org.example.Util;


public class KeywordAutomata extends PreDefinedSequenceAutomata {

    public KeywordAutomata() {
        super(Util.keyWordTokens);
    }

}
