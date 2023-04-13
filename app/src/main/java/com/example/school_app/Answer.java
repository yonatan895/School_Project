package com.example.school_app;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Answer {

  @SerializedName("text")
  @Expose
  private String text;

  @SerializedName("correct")
  @Expose
  private boolean correct;

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Answer withText(String text) {
    this.text = text;
    return this;
  }

  public boolean isCorrect() {
    return correct;
  }

  public void setCorrect(boolean correct) {
    this.correct = correct;
  }

  public Answer withCorrect(boolean correct) {
    this.correct = correct;
    return this;
  }
}
