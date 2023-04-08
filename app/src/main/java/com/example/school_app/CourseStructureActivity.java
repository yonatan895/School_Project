package com.example.school_app;

import android.os.Bundle;

public class CourseStructureActivity extends BaseActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_course_structure);
    setupNavigationDrawer(R.id.structure_drawer_layout, getApplicationContext());
    setupBottomNavigationMenu(R.id.bottom_navigation, getApplicationContext());
  }
}
