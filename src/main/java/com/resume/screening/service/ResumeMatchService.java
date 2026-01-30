package com.resume.screening.service;

import com.resume.screening.ml.*;
import com.resume.screening.model.MatchResult;
import com.resume.screening.parser.ResumeParser;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

public class ResumeMatchService {

    public MatchResult match(MultipartFile resume, MultipartFile jd) throws Exception {

        String resumeText = ResumeParser.extractText(resume);
        String jdText = ResumeParser.extractText(jd);

        List<String> resumeWords = TextPreprocessor.preprocess(resumeText);
        List<String> jdWords = TextPreprocessor.preprocess(jdText);

        // TF-IDF similarity
        Map<String, Double> tfResume = TfIdfVectorizer.tf(resumeWords);
        Map<String, Double> tfJd = TfIdfVectorizer.tf(jdWords);

        Map<String, Double> idf =
                TfIdfVectorizer.idf(List.of(resumeWords, jdWords));

        Map<String, Double> v1 =
                TfIdfVectorizer.tfidf(tfResume, idf);
        Map<String, Double> v2 =
                TfIdfVectorizer.tfidf(tfJd, idf);

        double score = CosineSimilarity.compute(v1, v2);

        // 🔥 AUTO skill extraction from JD
        List<String> jdSkills =
                AutoSkillExtractor.extractSkills(jdWords, idf, 10);

        Set<String> resumeSet = new HashSet<>(resumeWords);

        List<String> matched = new ArrayList<>();
        List<String> missing = new ArrayList<>();

        for (String skill : jdSkills) {
            if (resumeSet.contains(skill)) {
                matched.add(skill);
            } else {
                missing.add(skill);
            }
        }

        return new MatchResult(score, matched, missing);
    }
}
