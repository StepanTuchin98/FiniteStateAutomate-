package com.automate;

import com.automate.model.FiniteStateAutomate;
import com.automate.model.Pair;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ProgramTask1 {

    public static final String INPUT = "C:\\Users\\1\\Translations\\src\\com\\automate\\resources\\automate_task_1\\input.txt";
    public static final String OUTPUT = "C:\\Users\\1\\Translations\\src\\com\\automate\\resources\\automate_task_1\\output.txt";
    public static final String TASK_1 = "automate_task_1";

    public static void main(String[] args) throws IOException {
        FiniteStateAutomate finiteStateAutomate = new FiniteStateAutomate(TASK_1);

        int pos = 0;
        StringBuilder str = new StringBuilder();
        String digit = new String(Files.readAllBytes(Paths.get(INPUT)));

        while (pos < digit.length()) {
            Pair answer = FiniteStateAutomate.MaxString(finiteStateAutomate, digit, pos);

            if (answer.isExist()) {
                String resultString = digit.substring(pos, pos + answer.getSize());

                if (answer.getSize() > 0) {
                    pos += answer.getSize();
                    str.append(resultString).append(System.lineSeparator());
                    System.out.println(resultString);
                } else
                    pos++;
            } else {
                pos++;
            }
        }

        FileOutputStream outputStream = new FileOutputStream(OUTPUT);
        byte[] strToBytes = new String(str).getBytes();
        outputStream.write(strToBytes);
    }
}
