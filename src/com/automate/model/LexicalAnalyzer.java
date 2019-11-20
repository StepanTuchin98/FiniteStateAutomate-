package com.automate.model;

import java.util.ArrayList;
import java.util.List;

import static com.automate.model.FiniteStateAutomate.MaxString;

public class LexicalAnalyzer {

    public static final String ERROR = "error";

    public static List<PairTokenClassToken> getTokens(List<TokenClass> tokenClasses, String text) {
        List<PairTokenClassToken> result = new ArrayList<>();

        int position = 0;
        while (position < text.length()) {
            String currentTokenClass = "";
            int currentPriority = 0;
            int maxLength = 0;

            for (TokenClass tokenClass : tokenClasses) {
                Pair foundedWord = MaxString(tokenClass.getAutomate(), text, position);
                if (foundedWord.isExist()) {
                    if (maxLength < foundedWord.getSize()) {
                        currentTokenClass = tokenClass.getName();
                        currentPriority = tokenClass.getPriority();
                        maxLength = foundedWord.getSize();
                    } else if (maxLength == foundedWord.getSize() && currentPriority < tokenClass.getPriority()) {
                        currentTokenClass = tokenClass.getName();
                        currentPriority = tokenClass.getPriority();
                        maxLength = foundedWord.getSize();
                    }
                }
            }

            if (maxLength > 0) {
                result.add(new PairTokenClassToken(currentTokenClass, text.substring(position, position + maxLength)));

                position += maxLength;
            } else if (maxLength == 0) {
                result.add(new PairTokenClassToken(ERROR, text.substring(position, position + 1)));

                position++;
            }
        }

        return result;
    }
}