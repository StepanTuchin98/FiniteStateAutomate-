package com.automate.model;

public class Transition {
    public Transition(String initialState, String finalState, String signal) {
        this.initialState = initialState;
        this.finalState = finalState;
        this.signal = signal;
    }

    private String initialState;

    private String finalState;

    private String signal;

    public String getInitialState() {
        return initialState;
    }

    public void setInitialState(String initialState) {
        this.initialState = initialState;
    }

    public String getFinalState() {
        return finalState;
    }

    public void setFinalState(String finalState) {
        this.finalState = finalState;
    }

    public String getSignal() {
        return signal;
    }

    public void setSignal(String signal) {
        this.signal = signal;
    }
}
