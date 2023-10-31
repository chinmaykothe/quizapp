package com.axelblaze.quizapp.dtos;

import com.axelblaze.quizapp.enums.Category;
import com.axelblaze.quizapp.enums.Difficulty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QuestionRequest {
    private Integer id;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String correctAnswer;
    private Difficulty difficultyLevel;
    private Category category;
}
