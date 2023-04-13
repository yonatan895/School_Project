package com.example.school_app;

public class UserModel {
  private String email;
  private int totalCorrectAnswers;

  private int totalQuestions;

  public UserModel() {}

  public UserModel(String email, int totalCorrectAnswers, int totalQuestions) {
    this.email = email;
    this.totalCorrectAnswers = totalCorrectAnswers;
    this.totalQuestions = totalQuestions;
  }

  public String getEmail() {
    return email;
  }

  public int getTotalCorrectAnswers() {
    return totalCorrectAnswers;
  }

  public double getAccuracy() {
    if (totalQuestions == 0) return 0;
    return (totalCorrectAnswers * 100 / totalQuestions);
  }

  public int getTotalQuestions() {
    return totalQuestions;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  /*public void updateLeaderboard(Quiz quiz, int numCorrectAnswers) {
      final int totalQuestions = quiz.getNumQuestions();
      final double accuracy = (double) numCorrectAnswers / totalQuestions;
      final double totalAccuracy = this.averageAccuracy * this.totalCorrectAnswers;
      this.totalCorrectAnswers += numCorrectAnswers;
      this.averageAccuracy = (totalAccuracy + accuracy) / (this.totalCorrectAnswers);
  }*/
}
