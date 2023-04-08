package com.example.school_app;


import android.os.Bundle;

public class TextEditorActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_text_editor);
    setupNavigationDrawer(R.id.text_editor_drawer_layout, getApplicationContext());
  }
}
