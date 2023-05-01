package com.example.school_app;

import android.os.Bundle;

public class Java6Activity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_java6);
    setupNavigationDrawer(R.id.java6_drawer_layout, getApplicationContext());
    setupBottomNavigationMenu(R.id.bottom_navigation, getApplicationContext());
  }
}
