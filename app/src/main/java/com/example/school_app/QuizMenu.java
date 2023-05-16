package com.example.school_app;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

/**
 * The type Quiz launcher.
 */
public class QuizMenu extends BaseActivity {
    /**
     * The Introcard.
     */
    MaterialCardView introcard,
    /**
     * The Java 7 card.
     */
    java7card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_menu);
        introcard = findViewById(R.id.introCard);
        java7card = findViewById(R.id.Java7_quiz_launcher);
        setupNavigationDrawer(R.id.quiz_launcher_layout, getApplicationContext());
        introcard.setOnClickListener(v -> startQuizLauncher("Overview of Java"));

    }
    /**
     * The onBackPressed function is a function that allows the user to exit the app by pressing
     * the back button. The function displays an alert dialog asking if they are sure they want to
     * exit, and then exits if yes is pressed. If no is pressed, it dismisses the dialog and returns
     * them to their previous activity (in this case QuizMenu). This was implemented because I felt like it would be more intuitive for users than having nothing happen when they press back on this screen. It also prevents them from accidentally exiting out of my app when trying to go back one screen in my navigation drawer menu.
     *
     */
    @Override
    public void onBackPressed() {
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(QuizMenu.this);
        materialAlertDialogBuilder.setTitle(R.string.app_name);
        materialAlertDialogBuilder.setMessage("Are you sure you want to exit to the main app? There are plenty of quizzes for you :)");
        materialAlertDialogBuilder.setNegativeButton(android.R.string.no, (dialog, which) -> dialog.dismiss());
        materialAlertDialogBuilder.setPositiveButton(android.R.string.yes, (dialog, which) -> finish());
        materialAlertDialogBuilder.show();


    }

    /**
     * The startQuizLauncher function is called when the user clicks on a quiz in the list of quizzes.
     * It takes as input a string representing the name of the quiz that was clicked, and it launches
     * an intent to start QuizLauncher with this information.

     *
     * @param quizName Pass the name of the quiz to be launched to quizlauncher
     *
     *
     */
    public void startQuizLauncher(String quizName) {
        Intent intent = new Intent(QuizMenu.this, QuizLauncher.class);
        intent.putExtra("quizName", quizName);
        startActivity(intent);
        finish();
    }
}