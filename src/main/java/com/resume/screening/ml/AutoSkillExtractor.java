package com.resume.screening.ml;

import java.util.*;
import java.util.stream.Collectors;

public class AutoSkillExtractor {

    // Extract top N important words from JD using TF-IDF
    public static List<String> extractSkills(
            List<String> jdWords,
            Map<String, Double> idf,
            int topN) {

        Map<String, Double> tf = TfIdfVectorizer.tf(jdWords);
        Map<String, Double> tfidf = TfIdfVectorizer.tfidf(tf, idf);

        return tfidf.entrySet()
                .stream()
                .sorted((a, b) -> Double.compare(b.getValue(), a.getValue()))
                .limit(topN)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
