package com.example.school_app;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;

import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.List;

import java.util.Objects;

public class LeaderboardActivity extends AppCompatActivity {


public class LeaderboardActivity extends BaseActivity {


  private ActionBarDrawerToggle actionBarDrawerToggle;
  private LeaderboardAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_leaderboard);

    // Initialize the RecyclerView
    RecyclerView recyclerView = findViewById(R.id.recyclerView);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    // Initialize the adapter and set it to the RecyclerView
    adapter = new LeaderboardAdapter();
    recyclerView.setAdapter(adapter);

    // Query the database to get the user data and update the adapter
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference usersRef = db.collection("User");
    Query query = usersRef.orderBy("totalCorrectAnswers", Query.Direction.DESCENDING);
    query
        .get()
        .addOnSuccessListener(
            queryDocumentSnapshots -> {
              List<UserModel> usersList = new ArrayList<>();
              for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                UserModel user = documentSnapshot.toObject(UserModel.class);
                usersList.add(user);
              }
              adapter.setUsersList(usersList);
            });

    DrawerLayout drawerLayout = findViewById(R.id.leaderboard_drawer_layout);
    actionBarDrawerToggle =
        new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
    drawerLayout.addDrawerListener(actionBarDrawerToggle);
    actionBarDrawerToggle.syncState();

    Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    NavigationView navigationView = findViewById(R.id.navigation_view);
    navigationView.setNavigationItemSelectedListener(
        item -> {
          int id = item.getItemId();
          Context context = getApplicationContext();
          if (id == R.id.nav_home) {
            startActivity(new Intent(context, IntroActivity.class));
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

    setupNavigationDrawer(R.id.leaderboard_drawer_layout, getApplicationContext());

  }
}
