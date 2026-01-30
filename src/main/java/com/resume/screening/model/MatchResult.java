package com.resume.screening.model;

import java.util.List;

public class MatchResult {

    private double matchScore;
    private List<String> matchedSkills;
    private List<String> missingSkills;

    public MatchResult(double matchScore,
                       List<String> matchedSkills,
                       List<String> missingSkills) {
        this.matchScore = matchScore;
        this.matchedSkills = matchedSkills;
        this.missingSkills = missingSkills;
    }

    public double getMatchScore() {
        return matchScore;
    }

    public List<String> getMatchedSkills() {
        return matchedSkills;
    }

    public List<String> getMissingSkills() {
        return missingSkills;
    }
}
