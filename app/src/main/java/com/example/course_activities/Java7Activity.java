package com.example.course_activities;

import android.os.Bundle;

import com.example.classes.BaseActivity;
import com.example.school_app.R;

public class Java7Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java7);
        setupNavigationDrawer(R.id.java7_drawer_layout, getApplicationContext());
        setupBottomNavigationMenu(R.id.bottom_navigation, getApplicationContext());
        highlightText(R.id.diamond_operator, "Map<String, List<Double>> nums = new HashMap<String, List<Double>>()");
        highlightText(R.id.java7_map, "Map<String, List<Double>> nums = new HashMap<>()");
        highlightText(R.id.string_switch, getResources().getString(R.string.switch_example));
        highlightText(R.id.auto_res_management, getResources().getString(R.string.auto_res_example));
        highlightText(R.id.improved_exc, getResources().getString(R.string.improved_ex_example));
        highlightText(R.id.num_in_under_exa, getResources().getString(R.string.num_in_under_exa));



    }
}
