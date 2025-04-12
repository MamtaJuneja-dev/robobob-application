package com.robobob.model;



public class QuestionRequest {
    private String question;

    // Constructor
    public QuestionRequest(String question) {
        this.question = question;
    }

    // Getter and Setter
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}

