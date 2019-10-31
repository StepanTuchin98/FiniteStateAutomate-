package com.automate;

import com.automate.model.FiniteStateAutomate;
import com.automate.model.Pair;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        FiniteStateAutomate finiteStateAutomate = new FiniteStateAutomate();

        int pos = 0;
        Scanner sc = new Scanner(System.in);
        String line = sc.next();

        while (pos < line.length()) {
            Pair answer = MaxString(finiteStateAutomate, line, pos);
            System.out.println("Max String exist "  + answer.isExist() + " Length is " + answer.getSize());
            if (answer.isExist()) {
                String outputString = line.substring(pos, pos + answer.getSize());
                System.out.println(outputString);
                pos += answer.getSize();
            } else {
                pos++;
            }
        }

    }

    public static Pair MaxString(FiniteStateAutomate a, String line, int position) {
        boolean flag = false;
        int maxLength = 0;
        String curState = a.getSetOfInitialStates().get(0);

        boolean isFinishState = false;
        if (a.getSetOfFinalStates().contains(curState)) {
            isFinishState = true;
        }

        for (int i = position; i < line.length(); i++) {
            if (containsSuchLetterInAlpha(a, line, i)) {
                return new Pair(flag, maxLength);
            } else if (!a.hasTransitionWithLetter(curState, Character.toString(line.charAt(i)))) {
                return new Pair(flag, maxLength);
            } else {
                curState = a.getNewState(curState, Character.toString(line.charAt(i)));
                maxLength++;
                flag = true;
                if (!a.getSetOfFinalStates().contains(curState)) {
                    isFinishState = true;
                } else {
                    flag = false;
                    isFinishState = false;
                }
            }
        }
        flag = isFinishState;

        return new Pair(flag, maxLength);
    }

    private static boolean containsSuchLetterInAlpha(FiniteStateAutomate a, String line, int i){
        return a.getAlphabet().indexOf(Character.toString(line.charAt(i))) == -1;
    }
}