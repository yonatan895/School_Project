package com.example.school_app;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import java.util.Objects;

public abstract class BaseActivity extends AppCompatActivity {
  private ActionBarDrawerToggle actionBarDrawerToggle;
  private final Class<?>[] activityList = {
    IntroActivity.class, JavaEcosystemActivity.class, CourseStructureActivity.class
  };

  protected void setupNavigationDrawer(int resource, Context context) {
    DrawerLayout drawerLayout = findViewById(resource);
    actionBarDrawerToggle =
        new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

    drawerLayout.addDrawerListener(actionBarDrawerToggle);
    actionBarDrawerToggle.syncState();

    Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    NavigationView navigationView = findViewById(R.id.navigation_view);
    navigationView.setNavigationItemSelectedListener(
        item -> {
          int id = item.getItemId();
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

  protected void setupBottomNavigationMenu(int resource, Context context) {
    BottomNavigationView bottomNavigationView = findViewById(resource);
    bottomNavigationView.setOnNavigationItemSelectedListener(
        item -> {
          int id = item.getItemId();
          if (id == R.id.previous) {
            int currentIndex = getCurrentActivityIndex();
            if (currentIndex > 0) {
              startActivity(new Intent(context, activityList[currentIndex - 1]));
              return true;
            }
          } else if (id == R.id.next) {
            int currentIndex = getCurrentActivityIndex();
            if (currentIndex < activityList.length - 1) {
              startActivity(new Intent(context, activityList[currentIndex + 1]));
              finish();
              return true;
            }

          } else if (id == R.id.text_editor) {
            startActivity(new Intent(context, TextEditorActivity.class));
            return true;
          }
          return false;
        });
  }

  private int getCurrentActivityIndex() {
    Class<?> currentClass = getClass();
    for (int i = 0; i < activityList.length; i++) {
      if (activityList[i].equals(currentClass)) {
        return i;
      }
    }
    return -1;
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
      actionBarDrawerToggle.syncState();
      return true;
    }


  private int getCurrentActivityIndex() {
    Class<?> currentClass = getClass();
    for (int i = 0; i < activityList.length; i++) {
      if (activityList[i].equals(currentClass)) {
        return i;
      }
    }
    return -1;
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
      actionBarDrawerToggle.syncState();
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
