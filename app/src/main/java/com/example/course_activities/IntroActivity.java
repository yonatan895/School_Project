package com.example.course_activities;



import android.content.Intent;
import android.os.Bundle;


import com.example.classes.BaseActivity;
import com.example.break_reminder.BreakReminderService;
import com.example.school_app.R;


public class IntroActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        Intent intent = new Intent(this, BreakReminderService.class);
        startService(intent);
        setupNavigationDrawer(R.id.my_drawer_layout, getApplicationContext());
        setupBottomNavigationMenu(R.id.bottom_navigation, getApplicationContext());

    }







}

