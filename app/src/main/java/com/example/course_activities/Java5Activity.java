package com.example.course_activities;

import android.os.Bundle;
import com.example.classes.BaseActivity;
import com.example.school_app.R;

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
    highlightText(R.id.concise_loop, getResources().getString(R.string.concise_example));
    highlightText(R.id.static_import, "import static java.lang.System.out");
    highlightText(R.id.print_spaced, "printSpaced(1,2,3)");
    highlightText(R.id.enums, "enum letter{A,B,C};");
    highlightText(
        R.id.var_args,
        "void printSpaced(Object ... objects){\n for(Object o: objects)\n out.print(o)");
    highlightText(R.id.enum_example, getResources().getString(R.string.enum_example));
  }
}
