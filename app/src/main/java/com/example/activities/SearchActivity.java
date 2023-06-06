package com.example.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.course_activities.BackportsActivity;
import com.example.course_activities.ClassesInJavaActivity;
import com.example.course_activities.CollectorActivity;
import com.example.course_activities.CommentsInJavaActivity;
import com.example.course_activities.ConcurrentActivity;
import com.example.course_activities.CourseStructureActivity;
import com.example.course_activities.DefaultsActivity;
import com.example.course_activities.FeaturesActivity;
import com.example.course_activities.ForEachActivity;
import com.example.course_activities.ForkActivity;
import com.example.course_activities.FunctionalActivity;
import com.example.course_activities.FuturesActivity;
import com.example.course_activities.GroovyGparsActivity;
import com.example.course_activities.GroupingActivity;
import com.example.course_activities.GuavaActivity;
import com.example.course_activities.HibernateActivity;
import com.example.course_activities.IntroActivity;
import com.example.course_activities.JShellActivity;
import com.example.course_activities.JVMBenefitsActivity;
import com.example.course_activities.Java12FeatureActivity;
import com.example.course_activities.Java5Activity;
import com.example.course_activities.Java6Activity;
import com.example.course_activities.Java7Activity;
import com.example.course_activities.Java8_vs_Java7Activity;
import com.example.course_activities.JavaEcosystemActivity;
import com.example.course_activities.LambdaExpActivity;
import com.example.course_activities.LocalActivity;
import com.example.course_activities.LogbackActivity;
import com.example.course_activities.ModularityActivity;
import com.example.course_activities.NewIOActivity;
import com.example.course_activities.PeekActivity;
import com.example.course_activities.PrimAndArrActivity;
import com.example.course_activities.STMActivity;
import com.example.course_activities.StreamsActivity;
import com.example.course_activities.WhatInJava8Activity;
import com.example.school_app.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchActivity extends AppCompatActivity {
    private ArrayAdapter<String> adapter;
    private HashMap<String, Class<?>> courseMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        courseMap = createActivityMap();

        SearchView searchView = findViewById(R.id.search_view);
        ListView listView = findViewById(R.id.list_view);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>());
        listView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return false;
            }
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String courseName = adapter.getItem(position);
            Class<?> activityClass = courseMap.get(courseName);

            if (activityClass != null) {
                startActivity(new Intent(SearchActivity.this, activityClass));
            } else {
                Toast.makeText(SearchActivity.this, "Course not found", Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * The filterList function is used to filter the list of courses based on user input.
     *
     * @param text Filter the list of courses
     */
    private void filterList(String text) {
        adapter.clear();
        if (TextUtils.isEmpty(text)) {
            adapter.addAll(getAllCourseNames());
        } else {
            text = text.toLowerCase();
            for (String courseName : getAllCourseNames()) {
                if (courseName.toLowerCase().contains(text)) {
                    adapter.add(courseName);
                }
            }
        }
    }


    /**
     * The getAllCourseNames function returns an ArrayList of Strings containing the names of all
     * courses in the course list.
     *
     * @return An arraylist of course names
     */
    private ArrayList<String> getAllCourseNames() {
        ArrayList<String> courseNames = new ArrayList<>();
        for (Map.Entry<String, Class<?>> entry : courseMap.entrySet()) {
            String courseName = entry.getKey();
            courseName = courseName.replace("Activity", "");
            courseNames.add(courseName);
        }
        return courseNames;
    }


    /**
     * Creates a map of activity names to activity classes.
     *
     * @return The map of activity names to activity classes
     */
    @NonNull
    private HashMap<String, Class<?>> createActivityMap() {
        HashMap<String, Class<?>> map = new HashMap<>();
        map.put("Introduction to Java", IntroActivity.class);
        map.put("Java Ecosystem", JavaEcosystemActivity.class);
        map.put("Course Structure", CourseStructureActivity.class);
        map.put("Primitive Types and Arrays", PrimAndArrActivity.class);
        map.put("Classes in Java", ClassesInJavaActivity.class);
        map.put("Comments in Java", CommentsInJavaActivity.class);
        map.put("Java 5 Features", Java5Activity.class);
        map.put("Java 6 Features", Java6Activity.class);
        map.put("Java 7 Features", Java7Activity.class);
        map.put("Fork/Join Framework", ForkActivity.class);
        map.put("New I/O", NewIOActivity.class);
        map.put("JVM Benefits", JVMBenefitsActivity.class);
        map.put("What's New in Java 8", WhatInJava8Activity.class);
        map.put("Lambda Expressions", LambdaExpActivity.class);
        map.put("Java 8 vs Java 7", Java8_vs_Java7Activity.class);
        map.put("Default Methods", DefaultsActivity.class);
        map.put("Streams", StreamsActivity.class);
        map.put("ForEach Method", ForEachActivity.class);
        map.put("Peek Method", PeekActivity.class);
        map.put("Collectors", CollectorActivity.class);
        map.put("Grouping", GroupingActivity.class);
        map.put("New Features in Java 9-16", FeaturesActivity.class);
        map.put("Functional Programming", FunctionalActivity.class);
        map.put("Backports", BackportsActivity.class);
        map.put("Modularity", ModularityActivity.class);
        map.put("JShell", JShellActivity.class);
        map.put("Local Variable Type Inference", LocalActivity.class);
        map.put("Java 12 Features", Java12FeatureActivity.class);
        map.put("Logback", LogbackActivity.class);
        map.put("Hibernate", HibernateActivity.class);
        map.put("Guava", GuavaActivity.class);
        map.put("Concurrency", ConcurrentActivity.class);
        map.put("Futures", FuturesActivity.class);
        map.put("Software Transactional Memory", STMActivity.class);
        map.put("Groovy and GPars", GroovyGparsActivity.class);
        return map;
    }

}
