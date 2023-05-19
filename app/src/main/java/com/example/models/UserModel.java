package com.example.models;


/**
 * The type User model.
 */
public class UserModel {
    private String email;
    private int totalCorrectAnswers;

    private int totalQuestions;

    /**
     * Instantiates a new User model.
     */
    public UserModel() {

    }


    /**
     * Instantiates a new User model.
     *
     * @param email               the email
     * @param totalCorrectAnswers the total correct answers
     * @param totalQuestions      the total questions
     */
    public UserModel(String email, int totalCorrectAnswers, int totalQuestions)
    {
        this.email = email;
        this.totalCorrectAnswers = totalCorrectAnswers;
        this.totalQuestions = totalQuestions;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * Gets total correct answers.
     *
     * @return the total correct answers
     */
    public int getTotalCorrectAnswers() {
        return totalCorrectAnswers;
    }

    /**
     * Gets accuracy.
     *
     * @return the accuracy
     */
    public double getAccuracy() {
        if(totalQuestions == 0) return 0;
        return (totalCorrectAnswers*100 / totalQuestions);
    }

    /**
     * Gets total questions.
     *
     * @return the total questions
     */
    public int getTotalQuestions() { return totalQuestions; }

    /**
     * Sets email.
     *
     * @param email the email
     */
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
