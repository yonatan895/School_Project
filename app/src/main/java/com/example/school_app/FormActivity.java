package com.example.school_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.os.Bundle;
import android.widget.Button;


public class FormActivity extends AppCompatActivity {
    // Layout init
    ConstraintLayout layout;
    //Sign out button
    Button signOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        layout = findViewById(R.id.form);
        signOut = findViewById(R.id.sign_out);







    }

}