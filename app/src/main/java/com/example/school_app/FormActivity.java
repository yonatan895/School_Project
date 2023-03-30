package com.example.school_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import java.io.IOException;



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






        signOut.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(FormActivity.this, MainActivity.class));
        });
    }











    }

