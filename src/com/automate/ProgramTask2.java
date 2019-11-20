package com.automate;

import com.automate.model.LexicalAnalyzer;
import com.automate.model.TokenClass;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProgramTask2 {

    public static final String SIMPLE_AUTOMATE = "C:\\Users\\1\\Translations\\src\\com\\automate\\resources\\task2\\var.txt";
    public static final String KEYWORDS_AUTOMATE = "C:\\Users\\1\\Translations\\src\\com\\automate\\resources\\task2\\keywords.txt";
    public static final String TEXT = "C:\\Users\\1\\Translations\\src\\com\\automate\\resources\\task2\\input.txt";
    public static final String OUTPUT_TASK_2 = "C:\\Users\\1\\Translations\\src\\com\\automate\\resources\\task2\\output.txt";


    public static void main(String[] args) throws IOException {
        List<TokenClass> tokenClasses = new ArrayList<>();
        TokenClass simpleAutomate = new TokenClass(SIMPLE_AUTOMATE);
        TokenClass keyWordsAutomate = new TokenClass(KEYWORDS_AUTOMATE);
        String text = new String(Files.readAllBytes(Paths.get(TEXT))).replace(System.lineSeparator(), " ");

        tokenClasses.add(simpleAutomate);
        tokenClasses.add(keyWordsAutomate);

        String pairs = LexicalAnalyzer.getTokens(tokenClasses, text)
                .stream()
                .map(pair -> pair.getTokenClass() + System.lineSeparator() + pair.getToken())
                .collect(Collectors.joining(System.lineSeparator()));


        FileOutputStream outputStream = new FileOutputStream(OUTPUT_TASK_2);
        byte[] strToBytes = pairs.getBytes();
        outputStream.write(strToBytes);
    }
}
