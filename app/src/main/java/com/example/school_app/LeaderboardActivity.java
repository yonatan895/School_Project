package com.example.school_app;

import android.os.Bundle;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.List;

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
    setupNavigationDrawer(R.id.leaderboard_drawer_layout, getApplicationContext());
  }
}
