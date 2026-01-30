package com.resume.screening.ml;

import java.util.*;

public class SkillAnalyzer {

    private static final Set<String> SKILLS = Set.of(
            "java", "spring", "mysql", "git",
            "docker", "kafka", "python"
    );

    public static List<String> extractSkills(List<String> words) {
        Set<String> found = new HashSet<>();
        for (String w : words) {
            if (SKILLS.contains(w)) {
                found.add(w);
            }
        }
        return new ArrayList<>(found);
    }
}
