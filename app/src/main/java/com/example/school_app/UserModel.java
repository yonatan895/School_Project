package com.example.school_app;



public class UserModel {
    private String email;
    private int totalCorrectAnswers;
    private double averageAccuracy;


    public UserModel(String email, int totalCorrectAnswers, double averageAccuracy)
    {
        this.email = email;
        this.totalCorrectAnswers = totalCorrectAnswers;
        this.averageAccuracy = averageAccuracy;
    }

    public String getEmail()
    {
        return email;
    }

    public int getTotalCorrectAnswers() {
        return totalCorrectAnswers;
    }

    public double getAccuracy() {
        return averageAccuracy;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void updateLeaderboard(Quiz quiz, int numCorrectAnswers) {
        int totalQuestions = quiz.getNumQuestions();
        double accuracy = (double) numCorrectAnswers / totalQuestions;
        double totalAccuracy = this.averageAccuracy * this.totalCorrectAnswers;
        this.totalCorrectAnswers += numCorrectAnswers;
        this.averageAccuracy = (totalAccuracy + accuracy) / (this.totalCorrectAnswers);
    }
}
