package org.example.automata.operator;

import org.example.util.Util;
import org.example.automata.PreDefinedSequenceAutomata;

public class OperatorAutomata extends PreDefinedSequenceAutomata {

    public OperatorAutomata() {
        super(Util.operatorTokens);
    }
}
