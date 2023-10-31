package com.axelblaze.quizapp.controllers;

import com.axelblaze.quizapp.dtos.QuestionRequest;
import com.axelblaze.quizapp.enums.Category;
import com.axelblaze.quizapp.models.Question;
import com.axelblaze.quizapp.services.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@AllArgsConstructor
public class QuestionsController {

    private QuestionService questionService;

    // Get all the Questions
    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>>getAllQuestions() {
        return questionService.getAllQuestions();
    }

    // Get all the questions by category
    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable Category category) {
        return  questionService.getQuestionsByCategory(category);
    }

    @PostMapping
    public ResponseEntity<Question> addQuestion(@RequestBody QuestionRequest request) {
        return questionService.addQuestion(request);
    }
}
