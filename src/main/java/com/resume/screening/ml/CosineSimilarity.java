package com.resume.screening.ml;

import java.util.Map;

public class CosineSimilarity {

    public static double compute(
            Map<String, Double> v1,
            Map<String, Double> v2) {

        if (v1.isEmpty() || v2.isEmpty()) {
            return 0.0;
        }

        double dot = 0.0;
        double mag1 = 0.0;
        double mag2 = 0.0;

        for (String key : v1.keySet()) {
            double a = v1.get(key);
            double b = v2.getOrDefault(key, 0.0);
            dot += a * b;
            mag1 += a * a;
        }

        for (double val : v2.values()) {
            mag2 += val * val;
        }

        double denominator = Math.sqrt(mag1) * Math.sqrt(mag2);
        if (denominator == 0.0) return 0.0;

        return dot / denominator;
    }
}
