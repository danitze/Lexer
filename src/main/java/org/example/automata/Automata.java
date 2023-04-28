package org.example.automata;

import org.example.State;
import org.example.TokenWithPosition;

public abstract class Automata {

    protected final State initialState;

    public Automata() {
        initialState = new State();
    }

    public abstract TokenWithPosition check(String line, int position);
}
