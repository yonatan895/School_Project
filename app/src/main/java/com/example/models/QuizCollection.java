package com.example.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class QuizCollection {

  @SerializedName("quizzes")
  @Expose
  private List<Quiz> quizzes;

  public List<Quiz> getQuizzes() {
    return quizzes;
  }

  public void setQuizzes(List<Quiz> quizzes) {
    this.quizzes = quizzes;
  }

  public QuizCollection withQuizzes(List<Quiz> quizzes) {
    this.quizzes = quizzes;
    return this;
  }
}
