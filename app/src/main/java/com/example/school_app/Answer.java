package com.example.school_app;

public class Answer {
    private final String answerText;
    private final boolean isCorrect;

    public Answer(String answerText, boolean isCorrect) {
        this.answerText = answerText;
        this.isCorrect = isCorrect;
    }
    public String getAnswerText() {
        return answerText;
    }
    public boolean isCorrect() {
        return isCorrect;
    }
}
