package com.axelblaze.quizapp.services;

import com.axelblaze.quizapp.dtos.QuizResponse;
import com.axelblaze.quizapp.dtos.ResponseDTO;
import com.axelblaze.quizapp.enums.Category;
import com.axelblaze.quizapp.models.Question;
import com.axelblaze.quizapp.models.Quiz;
import com.axelblaze.quizapp.repositories.QuestionRepository;
import com.axelblaze.quizapp.repositories.QuizRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuizService {

    private QuizRepository quizRepository;
    private QuestionRepository questionRepository;

    public ResponseEntity<Quiz> createQuiz(String title, Category category, int numQ) {

        List<Question> questions = questionRepository.findRandomQuestionByCategory(category, numQ);

        Quiz quiz = Quiz.builder()
                .title(title)
                .questions(questions)
                .build();

        return new ResponseEntity<>(quizRepository.save(quiz), HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuizResponse>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizRepository.findById(id);

//        Quiz quiz = quizRepository
//                .findById(id)
//                .orElseThrow(() -> new QuestionNotFound(id));
        List<Question> questionsFromDB = quiz.get().getQuestions();


        List<QuizResponse> questionForUser = new ArrayList<>();

        for(Question q : questionsFromDB) {
            QuizResponse qw = new QuizResponse(q.getId(), q.getQuestionTitle(), q.getOption1(),q.getOption2(), q.getOption3(), q.getOption4());
            questionForUser.add(qw);
        }
        return new ResponseEntity<>(questionForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<ResponseDTO> responses) {
        Quiz quiz = quizRepository.findById(id).get();
        List<Question> questions = quiz.getQuestions();

        int right = 0;
        int i = 0;

        for(ResponseDTO response: responses) {
            if(response.getResponse().equals(questions.get(i).getCorrectAnswer()))
                right++;

            i++;
        }

        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
