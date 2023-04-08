package com.example.school_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class CourseStructureActivity extends AppCompatActivity {
    private ActionBarDrawerToggle actionBarDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_structure);
        DrawerLayout drawerLayout = findViewById(R.id.structure_drawer_layout);
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
        // Temporary, just in order to test the different activities
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button next = findViewById(R.id.next_button);
        next.setOnClickListener(v -> startActivity(new Intent(this, JavaEcosystemActivity.class)));

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


}
