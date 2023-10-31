package com.axelblaze.quizapp.services;

import com.axelblaze.quizapp.enums.Category;
import com.axelblaze.quizapp.repositories.QuestionRepository;
import com.axelblaze.quizapp.dtos.QuestionRequest;
import com.axelblaze.quizapp.exceptions.QuestionAlreadyExistsException;
import com.axelblaze.quizapp.models.Question;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuestionService {

    private QuestionRepository questionRepository;
    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(Category category) {
        try {
            return new ResponseEntity<>(questionRepository.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Question> addQuestion(QuestionRequest request) {
        String title = request.getQuestionTitle();
        Optional<Question> existingQuestion = questionRepository.findByQuestionTitle(title);
        if(existingQuestion.isPresent()) {
            throw new QuestionAlreadyExistsException(title);
        }


        //create the customer
        Question question = Question.builder()
                .questionTitle(request.getQuestionTitle())
                .option1(request.getOption1())
                .option2((request.getOption2()))
                .option3(request.getOption3())
                .option4(request.getOption4())
                .correctAnswer((request.getCorrectAnswer()))
                .difficultyLevel(request.getDifficultyLevel())
                .category(request.getCategory())
                .build();

        return new ResponseEntity<>(questionRepository.save(question), HttpStatus.CREATED);
    }
}
