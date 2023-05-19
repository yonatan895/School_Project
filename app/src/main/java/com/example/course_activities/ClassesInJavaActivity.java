package com.example.course_activities;

import android.os.Bundle;
import com.example.classes.BaseActivity;
import com.example.school_app.R;

public class ClassesInJavaActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_classes_in_java);
    setupNavigationDrawer(R.id.classes_drawer_layout, getApplicationContext());
    setupBottomNavigationMenu(R.id.bottom_navigation, getApplicationContext());
    highlightText(R.id.class_demo_view, getResources().getString(R.string.class_demo));
    highlightText(R.id.class_small, getResources().getString(R.string.class_small_code));
    highlightText(R.id.class_exer_view, getResources().getString(R.string.class_exercise));
  }
}
