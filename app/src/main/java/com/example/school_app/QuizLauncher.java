package com.example.school_app;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

/** The type Quiz launcher. */
public class QuizLauncher extends BaseActivity {
  /** The Introcard. */
  MaterialCardView introcard,
      /** The Java 7 card. */
      java7card;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_quiz_launcher);
    introcard = findViewById(R.id.introCard);
    java7card = findViewById(R.id.Java7_quiz_launcher);
    setupNavigationDrawer(R.id.quiz_launcher_layout, getApplicationContext());
    introcard.setOnClickListener(
        v -> startActivity(new Intent(QuizLauncher.this, IntroQuizActivity.class)));
  }

  @Override
  public void onBackPressed() {
    // Dialog when user presses the back button
    MaterialAlertDialogBuilder materialAlertDialogBuilder =
        new MaterialAlertDialogBuilder(QuizLauncher.this);
    materialAlertDialogBuilder.setTitle(R.string.app_name);
    materialAlertDialogBuilder.setMessage("Are you sure you want to exit the app?");
    materialAlertDialogBuilder.setNegativeButton(
        android.R.string.no, (dialog, which) -> dialog.dismiss());
    materialAlertDialogBuilder.setPositiveButton(android.R.string.yes, (dialog, which) -> finish());
    materialAlertDialogBuilder.show();
  }
}
