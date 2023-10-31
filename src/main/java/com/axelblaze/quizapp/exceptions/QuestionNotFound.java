package com.axelblaze.quizapp.exceptions;

public class QuestionNotFound extends RuntimeException{
    public QuestionNotFound(Integer id) {
        super("Question with id: " + id + " not found ");
    }
}
