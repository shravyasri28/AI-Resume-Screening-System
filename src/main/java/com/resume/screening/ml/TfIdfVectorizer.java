package com.resume.screening.ml;

import java.util.*;

public class TfIdfVectorizer {

    public static Map<String, Double> tf(List<String> words) {
        Map<String, Double> tf = new HashMap<>();
        if (words.isEmpty()) return tf;

        for (String w : words) {
            tf.put(w, tf.getOrDefault(w, 0.0) + 1);
        }

        int size = words.size();
        tf.replaceAll((k, v) -> v / size);
        return tf;
    }

    public static Map<String, Double> idf(List<List<String>> documents) {
        Map<String, Double> idf = new HashMap<>();
        int totalDocs = documents.size();

        for (List<String> doc : documents) {
            for (String word : new HashSet<>(doc)) {
                idf.put(word, idf.getOrDefault(word, 0.0) + 1);
            }
        }

        // SMOOTHED IDF
        idf.replaceAll((k, v) ->
                Math.log((double) (totalDocs + 1) / (v + 1)) + 1
        );

        return idf;
    }

    public static Map<String, Double> tfidf(
            Map<String, Double> tf,
            Map<String, Double> idf) {

        Map<String, Double> tfidf = new HashMap<>();
        for (String word : tf.keySet()) {
            tfidf.put(word, tf.get(word) * idf.getOrDefault(word, 0.0));
        }
        return tfidf;
    }
}
