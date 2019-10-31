package com.automate.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

public class FiniteStateAutomate {
    private List<String> setOfAllStates;

    private List<String> setOfInitialStates;

    private List<String> setOfFinalStates;

    private List<String> alphabet;

    private List<Transition> matrixTransition;

    public FiniteStateAutomate() throws IOException {
        setFinalStates();
        setInitialStates();
        setAllStates();
        setAlphabet();
        setMatrix();
    }

    private ArrayList<String> getStatesFromFile(String file) throws IOException {
        String[] states = Files.lines(Paths.get(file), UTF_8)
                .collect(Collectors.joining(System.lineSeparator())).toLowerCase().split(" ");
        return new ArrayList<>(Arrays.asList(states));
    }

    private void setFinalStates() throws IOException {
        this.setSetOfFinalStates(getStatesFromFile("src\\com\\automate\\resources\\FinalStates.txt"));
    }

    private void setInitialStates() throws IOException {
        this.setSetOfInitialStates(getStatesFromFile("src\\com\\automate\\resources\\InitialStates.txt"));
    }

    private void setAllStates() throws IOException {
        this.setSetOfAllStates(getStatesFromFile("src\\com\\automate\\resources\\AllStates.txt"));
    }

    private void setAlphabet() throws IOException {
        List<String> signals = Arrays.asList(Files.lines(Paths.get("src\\com\\automate\\resources\\Signals.txt"), UTF_8)
                .collect(Collectors.joining(System.lineSeparator())).toLowerCase().split(" "));
        this.setAlphabet(signals);
    }

    private void setMatrix() throws IOException {
        String[] lines = Files.lines(Paths.get("src\\com\\automate\\resources\\Transitions.txt"), UTF_8)
                .collect(Collectors.joining(System.lineSeparator())).toLowerCase().split("\r\n");
        List<Transition> transitions = Arrays.stream(lines).map(line -> {
            String[] transition = line.split(" ");
            return new Transition(transition[0], transition[2], transition[1]);
        }).collect(Collectors.toList());
        this.setMatrixTransition(transitions);
    }

    public boolean hasTransitionWithLetter(String state, String signal) {
        return matrixTransition.stream().anyMatch(row -> row.getSignal().equals(signal)
                && row.getInitialState().equals(state));
    }

    public String getNewState(String state, String signal) {
        return matrixTransition.stream().filter(row -> row.getSignal().equals(signal)
                && row.getInitialState().equals(state)).findFirst().get().getFinalState();
    }

    public List<String> getSetOfAllStates() {
        return setOfAllStates;
    }

    public List<String> getSetOfInitialStates() {
        return setOfInitialStates;
    }

    public List<String> getSetOfFinalStates() {
        return setOfFinalStates;
    }

    public List<String> getAlphabet() {
        return alphabet;
    }

    public List<Transition> getMatrixTransition() {
        return matrixTransition;
    }

    public void setSetOfAllStates(List<String> setOfAllStates) {
        this.setOfAllStates = setOfAllStates;
    }

    public void setSetOfInitialStates(List<String> setOfInitialStates) {
        this.setOfInitialStates = setOfInitialStates;
    }

    public void setSetOfFinalStates(List<String> setOfFinalStates) {
        this.setOfFinalStates = setOfFinalStates;
    }

    public void setAlphabet(List<String> alphabet) {
        this.alphabet = alphabet;
    }

    public void setMatrixTransition(List<Transition> matrixTransition) {
        this.matrixTransition = matrixTransition;
    }
}