package com.example.school_app;

import java.util.List;

public class Quiz {
  private List<Question> questions;

  public Quiz(List<Question> questions) {
    this.questions = questions;
  }

  public List<Question> getQuestions() {
    return questions;
  }

  public int getNumQuestions() {
    return this.questions.size();
  }
}

class Question {
  private String questionText;
  private List<String> options;
  private int correctAnswerIndex;

  public Question(String questionText, List<String> options, int correctAnswerIndex) {
    this.questionText = questionText;
    this.options = options;
    this.correctAnswerIndex = correctAnswerIndex;
  }

  public String getQuestionText() {
    return questionText;
  }

  public List<String> getOptions() {
    return options;
  }

  public int getCorrectAnswerIndex() {
    return correctAnswerIndex;
  }
}
