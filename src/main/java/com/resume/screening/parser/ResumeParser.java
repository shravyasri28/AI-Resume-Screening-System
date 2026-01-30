package com.resume.screening.parser;

import org.apache.tika.Tika;
import org.springframework.web.multipart.MultipartFile;

public class ResumeParser {

    private static final Tika tika = new Tika();

    public static String extractText(MultipartFile file) throws Exception {
        return tika.parseToString(file.getInputStream());
    }
}
