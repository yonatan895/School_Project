package com.example.course_activities;

import android.os.Bundle;
import com.example.classes.BaseActivity;
import com.example.school_app.R;

public class ForkActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_fork);
    setupNavigationDrawer(R.id.fork_drawer_layout, getApplicationContext());
    setupBottomNavigationMenu(R.id.bottom_navigation, getApplicationContext());
  }
}
