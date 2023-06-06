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
        highlightText(R.id.fork_code1, getResources().getString(R.string.fork_exa_code1));
        highlightText(R.id.fork_code2, getResources().getString(R.string.fork_exa_code2));


    }
}