package com.axelblaze.quizapp.models;

import com.axelblaze.quizapp.enums.Category;
import com.axelblaze.quizapp.enums.Difficulty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
