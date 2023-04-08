package com.example.school_app;

import android.os.Bundle;

public class PrimAndArrActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_prim_and_arr);
    setupNavigationDrawer(R.id.prim_drawer_layout, getApplicationContext());
    setupBottomNavigationMenu(R.id.bottom_navigation, getApplicationContext());
    highlightText(R.id.arr_demo_view, getResources().getString(R.string.arr_demo));
  }
}
