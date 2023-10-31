package com.axelblaze.quizapp.exceptions;

public class QuestionAlreadyExistsException extends RuntimeException {
    public QuestionAlreadyExistsException(String title) {
        super("Question already exists with title: " + title);
    }
}
