package com.example.school_app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TextEditorActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_text_editor);
    setupNavigationDrawer(R.id.text_editor_drawer_layout, getApplicationContext());
    EditText code = findViewById(R.id.editText);
    Button execute = findViewById(R.id.submitButton);
    TextView output = findViewById(R.id.outputTextView);
  }
}
