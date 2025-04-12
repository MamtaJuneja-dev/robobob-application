package com.robobob.controller;

import com.robobob.model.QuestionRequest;
import com.robobob.service.RoboBobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/askQuestions")
public class RoboBobController {

    private final RoboBobService roboBobService;

    @Autowired
    public RoboBobController(RoboBobService roboBobService) {
        this.roboBobService = roboBobService;
    }

    @PostMapping
    public String askQuestion(@RequestBody QuestionRequest request) {
        return roboBobService.handleQuestion(request.getQuestion());
    }
}

