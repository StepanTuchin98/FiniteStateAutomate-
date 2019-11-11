package com.automate;

import com.automate.model.FiniteStateAutomate;
import com.automate.model.Pair;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Main {

    public static void main(String[] args) throws IOException {
        FiniteStateAutomate finiteStateAutomate = new FiniteStateAutomate();

        int pos = 0;
        StringBuilder str = new StringBuilder();
        String digit = new String(Files.readAllBytes(Paths.get("/Users/stuchin/Desktop/FiniteStateAutomate-/src/com/automate/resources/input.txt")));

        while (pos < digit.length()) {
            Pair answer = MaxString(finiteStateAutomate, digit, pos);

            if (answer.isExist()) {
                String resultString = digit.substring(pos, pos + answer.getSize());

                if (answer.getSize() > 0) {
                    pos += answer.getSize();
                    str.append(resultString).append("\n");
                    System.out.println(resultString);
                }
                else
                    pos++;
            } else {
                pos++;
            }
        }
        FileOutputStream outputStream = new FileOutputStream("/Users/stuchin/Desktop/FiniteStateAutomate-/src/com/automate/resources/output.txt");
        byte[] strToBytes = new String(str).getBytes();
        outputStream.write(strToBytes);

    }

    public static Pair MaxString(FiniteStateAutomate a, String number, int position) {
        boolean result = false;
        int maxLength = 0;
        String curState = a.getSetOfInitialStates().get(0);

        if (a.getSetOfFinalStates().contains(curState)) {
            result = true;
        }

        for (int i = position; i < number.length(); i++) {
            if (containsSuchLetterInAlpha(a, number, i)) {
                return new Pair(result, maxLength);
            } else if (!a.hasTransitionWithLetter(curState, Character.toString(number.charAt(i)))) {
                return new Pair(result, maxLength);
            } else {
                curState = a.getNewState(curState, Character.toString(number.charAt(i)));

                if (a.getSetOfFinalStates().contains(curState)) {
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
}
