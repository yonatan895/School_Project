package com.example.school_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Objects;

public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        db.collection("User").document(userId).get().addOnSuccessListener(documentSnapshot -> {
            ArrayList<Double> correctAnswers = (ArrayList<Double>) documentSnapshot.get("correct_answers");
            ArrayList<Double> accuracy = (ArrayList<Double>) documentSnapshot.get("accuracy");

            // Call a method to create the chart and pass the data to it
            //createChart(correctAnswers, accuracy);
        });

    }
}