package com.example.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Quiz {

  @SerializedName("name")
  @Expose
  private String name;

  @SerializedName("questions")
  @Expose
  private List<Question> questions;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Quiz withName(String name) {
    this.name = name;
    return this;
  }

  public List<Question> getQuestions() {
    return questions;
  }

  public void setQuestions(List<Question> questions) {
    this.questions = questions;
  }

  public Quiz withQuestions(List<Question> questions) {
    this.questions = questions;
    return this;
  }
}
