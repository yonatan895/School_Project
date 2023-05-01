package com.example.school_app;

import android.os.Bundle;

public class Java7Activity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_java7);
    setupNavigationDrawer(R.id.java7_drawer_layout, getApplicationContext());
    setupBottomNavigationMenu(R.id.bottom_navigation, getApplicationContext());
    highlightText(
        R.id.diamond_operator,
        "Map<String, List<Doubles>> nums = new HashMap<String, List<Double>> ()");
    highlightText(R.id.java7_map, "Map<String, List<Doubles>> nums = new HashMap<>");
    highlightText(R.id.string_switch, getResources().getString(R.string.switch_example));
  }
}
