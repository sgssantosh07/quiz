package com.example.quiz;

import java.util.List;

public class Question {
    private final String question;
    private final List<String> options;
    private final int correctIndex;

    public Question(String question, List<String> options, int correctIndex) {
        this.question = question;
        this.options = options;
        this.correctIndex = correctIndex;
    }

    public String getQuestion() { return question; }
    public List<String> getOptions() { return options; }
    public int getCorrectIndex() { return correctIndex; }
}
