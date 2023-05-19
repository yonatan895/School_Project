package com.example.course_activities;

import android.os.Bundle;

import com.example.classes.BaseActivity;
import com.example.school_app.R;

public class CommentsInJavaActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments_in_java);
        setupNavigationDrawer(R.id.comments_drawer_layout, getApplicationContext());
        setupBottomNavigationMenu(R.id.bottom_navigation, getApplicationContext());
        highlightText(R.id.comment_demo_view,getResources().getString(R.string.comment_demo));
    }
}
