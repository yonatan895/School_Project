package com.example.school_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

public class IntroActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_intro);
    Intent intent = new Intent(this, BreakReminderService.class);
    startService(intent);
    setupNavigationDrawer(R.id.my_drawer_layout, getApplicationContext());
    setupBottomNavigationMenu(R.id.bottom_navigation, getApplicationContext());
  }

  /**
   * The updateUserScore function updates the user's score in the database. It takes two parameters:
   * correctAnswers and totalQuestions, which are both integers. The function first checks if a user
   * is signed in, then it gets an instance of FirebaseFirestore and creates a reference to the
   * current user's document. Then it increments their totalCorrectAnswers by correctAnswers and
   * their totalQuestions by totalQuestions using FieldValue.increment(). Next, it gets that same
   * document again so that we can calculate averageAccuracy (totalCorrectAnswers/totalQuestions).
   * We do this because we don't want to have
   *
   * @param context Display a toast message to the user if there is an error updating their score
   * @param correctAnswers Increment the totalcorrectanswers field in the database
   * @param totalQuestions Calculate the average accuracy of a user
   * @docauthor Jonathan
   */
  public void updateUserScore(Context context, int correctAnswers, int totalQuestions) {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    if (user != null) {
      FirebaseFirestore db = FirebaseFirestore.getInstance();

      DocumentReference docRef = db.collection("User").document(user.getUid());

      docRef.update("totalCorrectAnswers", FieldValue.increment(correctAnswers));
      docRef.update("totalQuestions", FieldValue.increment(totalQuestions));
      docRef
          .get()
          .addOnSuccessListener(
              documentSnapshot -> {
                long totalCorrectAnswers = documentSnapshot.getLong("totalCorrectAnswers");
                double averageAccuracy = (totalCorrectAnswers * 1.0 / totalQuestions) * 100;
                docRef.update("accuracy", averageAccuracy);
              })
          .addOnFailureListener(
              e ->
                  Toast.makeText(
                          context,
                          "Error updating user score: " + e.getMessage(),
                          Toast.LENGTH_SHORT)
                      .show());
    } else {
      Toast.makeText(context, "Error updating user score: User not signed in", Toast.LENGTH_SHORT)
          .show();
    }
  }
}
