package com.example.school_app;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

public abstract class QuizActivity extends AppCompatActivity {

    private TextView mQuestionTextView;
    private RadioGroup mAnswersRadioGroup;

    private final ArrayList<Question> mQuestionsList = new ArrayList<>();
    private int mCurrentQuestionIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Initialize views
        mQuestionTextView = findViewById(R.id.question_text_view);
        mAnswersRadioGroup = findViewById(R.id.answers_radio_group);
        Button mSubmitButton = findViewById(R.id.submit_button);


        // Load questions
        setQuestionsList(getCollectionName());

        // Set submit button click listener
        mSubmitButton.setOnClickListener(view -> onAnswerSelected());


    }

    /*
    private void loadQuestions() {
        mFirestore.collection(getCollectionName())
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        for (DocumentSnapshot document : task.getResult()) {
                            Question question = document.toObject(Question.class);
                            mQuestionsList.add(question);
                        }
                        displayCurrentQuestion();
                    }
                });
    }
    */

    private void displayCurrentQuestion() {
        // Get current question
        Question currentQuestion = mQuestionsList.get(mCurrentQuestionIndex);

        // Set question text
        mQuestionTextView.setText(currentQuestion.getQuestionText());

        // Clear radio group
        mAnswersRadioGroup.removeAllViews();

        // Add answer options
        for (Answer answerOption : currentQuestion.getOptions()) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(answerOption.getAnswerText());
            mAnswersRadioGroup.addView(radioButton);
        }
    }

    private void onAnswerSelected() {
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
    private boolean isCorrectAnswer(Question question, int selectedAnswerIndex) {
        Answer selectedAnswer = question.getOptions().get(selectedAnswerIndex);
        return selectedAnswer.isCorrect();
    }

    // Load JSON file containing questions and answers for the quiz

    private JSONObject loadJsonFromAsset() {
        String json;
        try {
            // Json file is located inside the assets folder
            InputStream is = getAssets().open("quizzes.json");

            // Set the size of the buffer to the size of the JSON file, and read the buffer
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            // Convert the buffer to a string
            json = new String(buffer, StandardCharsets.UTF_8);

        // Catch any errors
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        try {
            return new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;


    } // Try setting the questions list from the JSON file
    protected void setQuestionsList(String collectionName) {
        JSONObject jsonObject = loadJsonFromAsset();
        JSONObject quiz = null;
        try {
            assert jsonObject != null;
            JSONArray quizArray = jsonObject.getJSONArray("quizzes");
            for (int i = 0; i < quizArray.length(); i++) {
                JSONObject quizObject = quizArray.getJSONObject(i);
                if (quizObject.getString("name").equals(collectionName)) {
                    quiz = quizObject;
                    break;
                }
            }

            // Loop through the questions and add them to the questions list
            assert quiz != null;
            JSONArray questionArray = quiz.getJSONArray("questions");
            for (int i = 0; i < questionArray.length(); i++) {

                // Parse the question and answers from the JSON file
                JSONObject questionObject = questionArray.getJSONObject(i);
                String questionText = questionObject.getString("question");
                JSONArray optionsArray = questionObject.getJSONArray("answers");
                ArrayList<Answer> options = new ArrayList<>();
                for (int j = 0; j < optionsArray.length(); j++) {
                    JSONObject answerObject = optionsArray.getJSONObject(j);
                    String answerText = answerObject.getString("text");
                    boolean isCorrect = answerObject.getBoolean("correct");
                    options.add(new Answer(answerText, isCorrect));
                }
                // Add the question to the questions list
                mQuestionsList.add(new Question(questionText, options));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        displayCurrentQuestion();

    }



    private void updateScore(boolean isAnswerCorrect) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            DocumentReference docRef = db.collection("User").document(user.getUid());
            if (isAnswerCorrect) {
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


    protected abstract void finishQuiz();

    protected abstract String getCollectionName();

}
