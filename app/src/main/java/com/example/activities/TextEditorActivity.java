package com.example.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.classes.BaseActivity;
import com.example.school_app.R;

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
