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
import com.pddstudio.highlightjs.HighlightJsView;
import com.pddstudio.highlightjs.models.Language;
import com.pddstudio.highlightjs.models.Theme;
import java.util.Objects;

public abstract class BaseActivity extends AppCompatActivity {
  ActionBarDrawerToggle actionBarDrawerToggle;
  // List of course activities
  private final Class<?>[] activityList = {
    IntroActivity.class,
    JavaEcosystemActivity.class,
    CourseStructureActivity.class,
    PrimAndArrActivity.class,
    ClassesInJavaActivity.class,
    CommentsInJavaActivity.class,
    Java5Activity.class,
    Java6Activity.class,
    Java7Activity.class,
    NewIOActivity.class,
    JVMBenefitsActivity.class,
    WhatInJava8Activity.class,
    LambdaExpActivity.class,
    Java8_vs_Java7Activity.class,
    DefaultsActivity.class,
    StreamsActivity.class,
    ForEachActivity.class,
    PeekActivity.class,
    CollectorActivity.class,
    GroupingActivity.class,
    FeaturesActivity.class,
    FunctionalActivity.class,
    BackportsActivity.class,
    ModularityActivity.class,
    JShellActivity.class,
    LocalActivity.class,
    Java12FeatureActivity.class,
    LogbackActivity.class,
    HibernateActivity.class,
    GuavaActivity.class,
    ConcurrentActivity.class,
    FuturesActivity.class,
    STMActivity.class,
    GroovyGparsActivity.class
  };
  // Setup navigation drawer
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
  // Setup bottom navigation menu, using the activity list to navigate between activities
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
  // Highlight snippets of code inside the course
  protected void highlightText(int resource, String text) {
    HighlightJsView highlightJsView = findViewById(resource);
    highlightJsView.setTheme(Theme.ANDROID_STUDIO);
    highlightJsView.setHighlightLanguage(Language.JAVA);
    highlightJsView.setSource(text);
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
