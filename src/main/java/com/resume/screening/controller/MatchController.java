package com.resume.screening.controller;

import com.resume.screening.model.MatchResult;
import com.resume.screening.service.ResumeMatchService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController //rest api controller
@RequestMapping("/match")
public class MatchController {

    @PostMapping
    public MatchResult match(
            @RequestParam MultipartFile resume,
            @RequestParam MultipartFile jd) throws Exception {

        return new ResumeMatchService().match(resume, jd);
    }
}
