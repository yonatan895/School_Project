package com.example.school_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class FinishedQuizActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_finished_quiz);
    setupNavigationDrawer(R.id.finished_quiz_drawer_layout, getApplicationContext());

    TextView scoreTextView = findViewById(R.id.scoreTextView);
    TextView totalQuestionsTextView = findViewById(R.id.totalQuestionsTextView);
    Button tryAgainButton = findViewById(R.id.tryAgainButton);
    Button exitButton = findViewById(R.id.exitButton);

    // Get the score and total questions from the bundle

    Intent intent = getIntent();
    Bundle bundle = intent.getBundleExtra("quizResults");
    int userScore = bundle.getInt("correctAnswers");
    int totalQuestions = bundle.getInt("totalQuestions");

    scoreTextView.setText(String.format("Score: %d", userScore));
    totalQuestionsTextView.setText(String.format("Total questions: %d", totalQuestions));

    tryAgainButton.setOnClickListener(
        view -> {
          Intent quizIntent = new Intent(this, IntroQuizActivity.class);
          startActivity(quizIntent);
          finish();
        });
    exitButton.setOnClickListener(
        view -> {
          Intent introIntent = new Intent(this, IntroActivity.class);
          startActivity(introIntent);
        });
  }
}
