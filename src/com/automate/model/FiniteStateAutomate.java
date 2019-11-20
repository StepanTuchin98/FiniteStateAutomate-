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
    public static final String prefixPath = "C:\\Users\\1\\Translations\\src\\com\\automate\\resources\\";
    private List<String> setOfAllStates;

    private List<String> setOfInitialStates;

    private List<String> setOfFinalStates;

    private List<String> alphabet;

    private List<Transition> matrixTransition;

    public FiniteStateAutomate(String path) throws IOException {
        setFinalStates(path);
        setInitialStates(path);
        setAllStates(path);
        setAlphabet(path);
        setMatrix(path);
    }

    private ArrayList<String> getStatesFromFile(String file) throws IOException {
        String[] states = Files.lines(Paths.get(file), UTF_8)
                .collect(Collectors.joining(System.lineSeparator())).toLowerCase().split(" ");
        return new ArrayList<>(Arrays.asList(states));
    }

    private void setFinalStates(String finalStatesPath) throws IOException {
        this.setSetOfFinalStates(getStatesFromFile(prefixPath + finalStatesPath + "\\FinalStates.txt"));
    }

    private void setInitialStates(String initialStatesPath) throws IOException {
        this.setSetOfInitialStates(getStatesFromFile(prefixPath + initialStatesPath + "\\InitialStates.txt"));
    }

    private void setAllStates(String allStatesPath) throws IOException {
        this.setSetOfAllStates(getStatesFromFile(prefixPath + allStatesPath + "\\AllStates.txt"));
    }

    private void setAlphabet(String alphabetPath) throws IOException {
        List<String> signals = Arrays.asList(Files.lines(Paths.get(prefixPath + alphabetPath + "\\Signals.txt"), UTF_8)
                .collect(Collectors.joining(System.lineSeparator())).toLowerCase().split(" "));
        this.setAlphabet(signals);
    }

    private void setMatrix(String matrixPath) throws IOException {
        String[] lines = Files.lines(Paths.get(prefixPath + matrixPath + "\\Transitions.txt"), UTF_8)
                .collect(Collectors.joining(System.lineSeparator())).toLowerCase().split(System.lineSeparator());
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
        if (matrixTransition.stream().anyMatch(row -> row.getSignal().equals(signal)
                && row.getInitialState().equals(state)))
            return matrixTransition.stream().filter(row -> row.getSignal().equals(signal)
                    && row.getInitialState().equals(state)).findFirst().get().getFinalState();
        return "";
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


    public static Pair MaxString(FiniteStateAutomate a, String text, int position) {
        boolean result = false;
        int maxLength = 0;
        List<String> currentStates = new ArrayList<>(a.getSetOfInitialStates());

        if (intersection(a.getSetOfFinalStates(), currentStates).size() != 0) {
            result = true;
        }

        for (int i = position; i < text.length(); i++) {
            if (containsSuchLetterInAlpha(a, text, i)) {
                return new Pair(result, maxLength);

            } else {
                int count = currentStates.size();
                for (int j = 0; j < count; j++) {
                    // if (a.hasTransitionWithLetter(currentStates.get(j), Character.toString(text.charAt(i))))
                    currentStates.set(j, a.getNewState(currentStates.get(j), Character.toString(text.charAt(i))));
                }

                currentStates = currentStates.stream().distinct().collect(Collectors.toList());
                if (intersection(a.getSetOfFinalStates(), currentStates).size() != 0) {
                    result = true;
                    maxLength = i + 1 - position;
                }
            }
        }

        return new Pair(result, maxLength);
    }

    private static boolean containsSuchLetterInAlpha(FiniteStateAutomate a, String line, int i) {
        return a.getAlphabet().indexOf(Character.toString(line.charAt(i))) == -1;
    }

    public static <T> List<T> intersection(List<T> list1, List<T> list2) {
        List<T> list = new ArrayList<T>();

        for (T t : list1) {
            if (list2.contains(t)) {
                list.add(t);
            }
        }

        return list;
    }
}