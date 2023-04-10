package com.example.school_app;

import android.os.Bundle;

public class Java5Activity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_java5);
    setupNavigationDrawer(R.id.java5_drawer_layout, getApplicationContext());
    setupBottomNavigationMenu(R.id.bottom_navigation, getApplicationContext());
    highlightText(R.id.generic_1, "List<String> strings = new ArrayList<String>();");
    highlightText(R.id.generic_2, "Map<Long, String> map = new HashMap<Long, String>();");
    highlightText(R.id.generic_3, "Map<Long, String> map = new HashMap<>();");
  }
}
