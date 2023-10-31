package com.axelblaze.quizapp.controllers;

import com.axelblaze.quizapp.dtos.QuizResponse;
import com.axelblaze.quizapp.dtos.ResponseDTO;
import com.axelblaze.quizapp.enums.Category;
import com.axelblaze.quizapp.models.Quiz;
import com.axelblaze.quizapp.services.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class QuizController {

    private QuizService quizService;


    @PostMapping("createQuiz")
    public ResponseEntity<Quiz> createQuiz(@RequestParam String title, @RequestParam Category category,
                                           @RequestParam int numQ) {

        return quizService.createQuiz(title, category, numQ);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuizResponse>> getQuizQuestions(@PathVariable Integer id) {
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<ResponseDTO> response) {
        return quizService.calculateResult(id, response);

    }

}
