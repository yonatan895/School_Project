package com.example.school_app;

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

public abstract class QuizActivity extends AppCompatActivity {

  private int correctAnswers = 0;

  private TextView mQuestionTextView;
  private RadioGroup mAnswersRadioGroup;

  private ArrayList<Question> mQuestionsList = new ArrayList<>();
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
    loadQuiz(getCollectionName());

    // Set submit button click listener
    mSubmitButton.setOnClickListener(view -> onAnswerSelected());
  }

  protected void displayCurrentQuestion() {
    mQuestionsList = new ArrayList<>(mQuestionsList.subList(0, 6));
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

  void onAnswerSelected() {
    // Get selected answer
    int selectedAnswerIndex = mAnswersRadioGroup.getCheckedRadioButtonId();

    // Check if answer is correct
    boolean isAnswerCorrect =
        isCorrectAnswer(mQuestionsList.get(mCurrentQuestionIndex), selectedAnswerIndex);

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
    Answer selectedAnswer = question.getAnswers().get(selectedAnswerIndex);
    return selectedAnswer.isCorrect();
  }

  // Load JSON file containing questions and answers for the quiz

  protected void setQuestionsList(String collectionName) throws IOException {
    try {
      Gson gson = new Gson();
      InputStream is = getAssets().open("quizzes.json");
      InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
      QuizCollection quizzes = gson.fromJson(isr, QuizCollection.class);
      assert quizzes != null;

      for (Quiz quiz : quizzes.getQuizzes()) {
        if (quiz.getName().equals(collectionName)) {
          mQuestionsList.addAll(quiz.getQuestions());
          break;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    displayCurrentQuestion();
  }

  void loadQuiz(String collectionName) {
    try {
      InputStream is = getAssets().open("quizzes.json");
      BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

      QuizCollection quizzes = new Gson().fromJson(reader, QuizCollection.class);
      mQuestionsList.addAll(quizzes.getQuizzes().get(0).getQuestions());
      for (int i = 0; i < mQuestionsList.size(); i++) {
        Log.d("QuizActivity", "loadQuiz: " + mQuestionsList.get(i).getQuestion());
      }

    } catch (IOException e) {
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
        correctAnswers++;
        docRef.update("totalCorrectAnswers", FieldValue.increment(1));
        docRef.update("totalQuestions", FieldValue.increment(1));
      } else {
        docRef.update("totalQuestions", FieldValue.increment(1));
      }

      // Update accuracy
      docRef
          .get()
          .addOnSuccessListener(
              documentSnapshot -> {
                int totalCorrectAnswers =
                    Objects.requireNonNull(documentSnapshot.getLong("totalCorrectAnswers"))
                        .intValue();
                int totalQuestions =
                    Objects.requireNonNull(documentSnapshot.getLong("totalQuestions")).intValue();
                double averageAccuracy = (totalCorrectAnswers * 1.0 / totalQuestions) * 100;
                docRef.update("accuracy", averageAccuracy);
              })
          .addOnFailureListener(e -> Log.d("TAG", "onFailure: " + e));
    }
  }

  // Start the finished quiz activity
  protected void finishQuiz() {
    Intent intent = new Intent(this, FinishedQuizActivity.class);
    Bundle bundle = new Bundle();
    bundle.putInt("correctAnswers", correctAnswers);
    bundle.putInt("totalQuestions", mQuestionsList.size());
    intent.putExtra("quizResults", bundle);
    startActivity(intent);
  }

  protected abstract String getCollectionName();
}
