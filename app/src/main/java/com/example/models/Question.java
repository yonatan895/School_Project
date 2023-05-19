package com.example.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Question {

  @SerializedName("question")
  @Expose
  private String question;

  @SerializedName("answers")
  @Expose
  private List<Answer> answers;

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public Question withQuestion(String question) {
    this.question = question;
    return this;
  }

  public List<Answer> getAnswers() {
    return answers;
  }

  public String getCorrectAnswer(List<Answer> answers) {
    for (Answer answer : answers) {
      if (answer.isCorrect()) {
        return answer.getText();
      }
    }
    return "";
  }

  public void setAnswers(List<Answer> answers) {
    this.answers = answers;
  }

  public Question withAnswers(List<Answer> answers) {
    this.answers = answers;
    return this;
  }
}
