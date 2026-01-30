package com.resume.screening.ml;

import java.util.*;

public class TextPreprocessor {

    private static final Set<String> STOPWORDS = Set.of(
    "and","or","the","with","for","to","of","in","on","by",
    "this","that","these","those",
    "job","company","employee","organization",
    "experience","role","position","work","working","gain","will","high","low","human"
);

public static List<String> preprocess(String text) {
    return Arrays.stream(text.toLowerCase().split("\\W+"))
            .filter(w -> w.length() > 2)
            .filter(w -> w.matches("[a-z]+"))
            .filter(w -> !STOPWORDS.contains(w))
            .toList();
}

}
