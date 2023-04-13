package com.example.school_app;

import java.util.List;

public class Quiz {
  private final String quizId;
  private final String quizTitle;
  private final List<Question> questions;

  public Quiz(String quizId, String quizTitle, List<Question> questions) {
    this.quizId = quizId;
    this.quizTitle = quizTitle;
    this.questions = questions;
  }

  public String getQuizId() {
    return quizId;
  }

  public String getQuizTitle() {
    return quizTitle;
  }

  public List<Question> getQuestions() {
    return questions;
  }
}
