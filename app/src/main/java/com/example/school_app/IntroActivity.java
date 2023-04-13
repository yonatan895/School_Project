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




   /* public void updateUserScore(Context context, int correctAnswers, int totalQuestions) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            DocumentReference docRef = db.collection("User").document(user.getUid());

            docRef.update("totalCorrectAnswers", FieldValue.increment(correctAnswers));
            docRef.update("totalQuestions", FieldValue.increment(totalQuestions));
            docRef.get().addOnSuccessListener(documentSnapshot -> {
                long totalCorrectAnswers = documentSnapshot.getLong("totalCorrectAnswers");
                double averageAccuracy = (totalCorrectAnswers *1.0/ totalQuestions) * 100;
                docRef.update("accuracy", averageAccuracy);
            }).addOnFailureListener(e -> Toast.makeText(context, "Error updating user score: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        } else {
            Toast.makeText(context, "Error updating user score: User not signed in", Toast.LENGTH_SHORT).show();
        }
    } */





}

