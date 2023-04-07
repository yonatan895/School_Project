package com.example.school_app;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import android.view.MenuItem;
import java.util.Objects;

public class FormActivity extends AppCompatActivity {

    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);


        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        DrawerLayout drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            Context context = getApplicationContext();
            if (id == R.id.nav_home) {
                startActivity(new Intent(context, FormActivity.class));
                return true;
            } else if (id == R.id.nav_sign_out) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(context, LoginActivity.class));
                return true;
            } else if (id == R.id.nav_leaderboard) {
                startActivity(new Intent(context, LeaderboardActivity.class));
                return true;
            }
            return false;
        });
    }

    // override the onOptionsItemSelected()
    // function to implement
    // the item click listener callback
    // to open and close the navigation
    // drawer when the icon is clicked
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            actionBarDrawerToggle.syncState();
            return true;
        }

            return super.onOptionsItemSelected(item);
        }





    public void updateUserScore(Context context, int correctAnswers, int totalQuestions) {
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
    }





}

