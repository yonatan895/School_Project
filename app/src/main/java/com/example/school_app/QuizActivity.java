package com.example.school_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

/**
 * The type Quiz activity.
 */
public abstract class QuizActivity extends AppCompatActivity {


    private int correctAnswers = 0;

    private TextView mQuestionTextView;
    private RadioGroup mAnswersRadioGroup;

    private final ArrayList<Question> mQuestionsList = setQuestionsList(getCollectionName());
    private int mCurrentQuestionIndex = 0;


    /**
     * Instantiates a new Quiz activity.
     */
    protected QuizActivity() {
    }


    /**
     * The displayCurrentQuestion function is responsible for displaying the current question to the user.
     * It does this by first getting the current question from mQuestionsList, then setting that text as
     * mQuestionTextView's text. Next, it clears all radio buttons in mAnswersRadioGroup and adds a new one for each answer option in our list of answers.
     * <p>
     *
     */
    protected void displayCurrentQuestion() {

        // Get current question
        Question currentQuestion = mQuestionsList.get(mCurrentQuestionIndex);


        // Set question text
        mQuestionTextView.setText(currentQuestion.getQuestion());

        // Clear radio group
        mAnswersRadioGroup.removeAllViews();

        // Add answer options
        for (Answer answerOption : currentQuestion.getAnswers()) {
            RadioButton radioButton = new RadioButton(this);
            Log.d("QuizActivity", "displayCurrentAnswer: " + radioButton.getText());
            radioButton.setText(answerOption.getText());
            mAnswersRadioGroup.addView(radioButton);
        }
    }

    /**
     * The onAnswerSelected function is called when the user selects an answer.
     * It checks if the selected answer is correct and updates the score accordingly.
     * If there are more questions, it displays them; otherwise, it finishes the quiz.
     * <p>
     *
     */
    void onAnswerSelected() {
        // Get selected answer
        int selectedAnswerIndex = mAnswersRadioGroup.getCheckedRadioButtonId();


        // Check if answer is correct
        boolean isAnswerCorrect = isCorrectAnswer(mQuestionsList.get(mCurrentQuestionIndex), selectedAnswerIndex);

        // Update current score
        updateScore(isAnswerCorrect);

        // Move to next question
        mCurrentQuestionIndex++;
        if (mCurrentQuestionIndex < mQuestionsList.size()) {
            displayCurrentQuestion();
        } else {
            finishQuiz();
        }
    }
    /**
     * The isCorrectAnswer function takes in a Question object and an integer representing the index of the selected answer.
     * It then returns true if the selected answer is correct, or false otherwise.

     *
     * @param  question Get the answers for that question
     * @param  selectedAnswerIndex Get the selected answer from the question
     *
     * @return A boolean value
     *
     */
    private boolean isCorrectAnswer(Question question, int selectedAnswerIndex) {
        Answer selectedAnswer = question.getAnswers().get(selectedAnswerIndex);
        return selectedAnswer.isCorrect();
    }




    /**
     * The setQuestionsList function takes in a string parameter, collectionName.
     * It then uses the Gson library to parse through the quizzes.json file and find
     * the quiz with a name that matches collectionName. Once it finds this quiz, it adds all of its questions to an ArrayList&lt;Question&gt; called mQuestionsList.
     *
     * @param collectionName Determine which quiz to load
     * @return An arraylist of question objects
     */
    protected ArrayList<Question> setQuestionsList(String collectionName) {
        try {
            Gson gson = new Gson();
            InputStream is = getAssets().open("quizzes.json");
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            QuizCollection quizzes = gson.fromJson(isr, QuizCollection.class);
            assert quizzes != null;

            for (Quiz quiz : quizzes.getQuizzes()) {
                if (quiz.getName().equals(collectionName)) {
                    assert mQuestionsList != null;
                    mQuestionsList.addAll(quiz.getQuestions());
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mQuestionsList;
    }



    /**
     * The updateScore function updates the user's score in Firebase.
     * It does this by first getting a reference to the database and then getting a reference to the current user.
     * If there is no current user, it will not update anything. Otherwise, it will get a document reference for that specific user from FirebaseFirestore db and then check if their answer was correct or incorrect using an if statement.
     * If their answer was correct, it increments totalCorrectAnswers by 1 and totalQuestions by 1 (since they answered one question). Otherwise, only totalQuestions is incremented since they did not get that question right. Then we
     *
     * @param isAnswerCorrect Determine if the answer is correct or not
     *
     */
    private void updateScore(boolean isAnswerCorrect) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            DocumentReference docRef = db.collection("User").document(user.getUid());
            if (isAnswerCorrect) {
                correctAnswers++;
                docRef.update("totalCorrectAnswers", FieldValue.increment(1));
                docRef.update("totalQuestions", FieldValue.increment(1));
            } else {
                docRef.update("totalQuestions", FieldValue.increment(1));
            }

            // Update accuracy
            docRef.get().addOnSuccessListener(documentSnapshot -> {
                    int totalCorrectAnswers = Objects.requireNonNull(documentSnapshot.getLong("totalCorrectAnswers")).intValue();
                    int totalQuestions = Objects.requireNonNull(documentSnapshot.getLong("totalQuestions")).intValue();
                    double averageAccuracy = (totalCorrectAnswers * 1.0 / totalQuestions) * 100;
                    docRef.update("accuracy", averageAccuracy);
                }).addOnFailureListener(e -> Log.d("TAG", "onFailure: " + e));
            }
        }


    /**
     * The finishQuiz function is called when the user has answered all of the questions in a quiz.
     * It creates an intent to start the FinishedQuizActivity, and passes it two pieces of information:
     * 1) The number of correct answers that were given by the user (correctAnswers).
     * 2) The total number of questions in this quiz (totalQuestions).
     * <p>
     *
     */
    protected  void finishQuiz() {
        Intent intent = new Intent(this, FinishedQuizActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("correctAnswers", correctAnswers);
        bundle.putInt("totalQuestions", mQuestionsList.size());
        intent.putExtra("quizResults", bundle);
        startActivity(intent);
    }

    /**
     * Gets collection name.
     *
     * @return the collection name
     */
    protected abstract String getCollectionName();

}
