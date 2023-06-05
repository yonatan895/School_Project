package com.example.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.classes.BaseActivity;
import com.example.school_app.R;
import java.util.ArrayList;
import java.util.HashMap;

public class SearchActivity extends AppCompatActivity {
  private ArrayAdapter<String> adapter;
  private HashMap<String, Class<?>> courseMap;

  private static final Class<?>[] activityList = BaseActivity.activityList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search);

    courseMap = createCourseMap();

    SearchView searchView = findViewById(R.id.search_view);
    ListView listView = findViewById(R.id.list_view);

    adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>());
    listView.setAdapter(adapter);

    searchView.setOnQueryTextListener(
        new SearchView.OnQueryTextListener() {
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

    listView.setOnItemClickListener(
        (parent, view, position, id) -> {
          String courseName = adapter.getItem(position);
          Class<?> activityClass = courseMap.get(courseName);

          if (activityClass != null) {
            Intent intent = new Intent(SearchActivity.this, activityClass);
            startActivity(intent);
          } else {
            Toast.makeText(SearchActivity.this, "Course not found", Toast.LENGTH_SHORT).show();
          }
        });
  }

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

  private ArrayList<String> getAllCourseNames() {
    ArrayList<String> courseNames = new ArrayList<>();
    for (Class<?> activityClass : activityList) {
      String courseName = activityClass.getSimpleName();
      courseNames.add(courseName);
    }
    return courseNames;
  }

  private HashMap<String, Class<?>> createCourseMap() {
    HashMap<String, Class<?>> map = new HashMap<>();
    for (Class<?> activityClass : activityList) {
      String courseName = activityClass.getSimpleName();
      map.put(courseName, activityClass);
    }
    return map;
  }
}
