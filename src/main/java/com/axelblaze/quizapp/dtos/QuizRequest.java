package com.axelblaze.quizapp.dtos;

import com.axelblaze.quizapp.models.Question;
import lombok.Data;

import java.util.List;

@Data
public class QuizRequest {
    private Integer id;
    private String title;
    private List<Question> questions;
}
