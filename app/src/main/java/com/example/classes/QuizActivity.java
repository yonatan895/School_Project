package com.example.classes;

import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.models.Answer;
import com.example.models.Question;
import com.example.models.Quiz;
import com.example.models.QuizCollection;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * The type Quiz activity.
 */
public abstract class QuizActivity extends AppCompatActivity {



    private final ArrayList<Question> mQuestionsList = new ArrayList<>();



    /**
     * Instantiates a new Quiz activity.
     */
    protected QuizActivity() {
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
            for (Quiz quiz : quizzes.getQuizzes()) {
                if (quiz.getName().equals(collectionName)) {
                    assert mQuestionsList != null;
                    Log.d("Questions length:", String.valueOf(mQuestionsList.size()));
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
     * The updateScore function updates the user's score in the database.
     *
     *
     * @param correctAnswers Update the totalcorrectanswers field in the database
     *
     *
     */
    protected void updateScore(int correctAnswers) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            DocumentReference docRef = db.collection("User").document(user.getUid());
            docRef.update("totalCorrectAnswers", FieldValue.increment(correctAnswers));
            int QUESTIONS_LENGTH = 6;
            docRef.update("totalQuestions", FieldValue.increment(QUESTIONS_LENGTH));


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


    protected void setQuestionScreen(int currentQuestionIndex, TextView question, TextView [] answerTextViews, ArrayList<Question> questions) {
         question.setText(questions.get(currentQuestionIndex).getQuestion());
         for (int i = 0; i < answerTextViews.length; i++) {
             answerTextViews[i].setText(questions.get(currentQuestionIndex).getAnswers().get(i).getText());

         }


    }




}
