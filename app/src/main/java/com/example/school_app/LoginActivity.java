package com.example.school_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.school_app.databinding.ActivityLoginBinding;
import com.google.firebase.auth.FirebaseAuth;



public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        binding.login.setOnClickListener(view -> {
            String email=binding.emailAddress.getText().toString().trim();
            String password=binding.password.getText().toString().trim();
            progressDialog.show();
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener(authResult -> {
                        progressDialog.cancel();
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, FormActivity.class));
                    })
                    .addOnFailureListener(e -> {
                        progressDialog.cancel();
                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    });

        });

        binding.resetPassword.setOnClickListener(view -> {
            String email=binding.emailAddress.getText().toString();
            progressDialog.setTitle("Sending Email...");
            progressDialog.show();
            firebaseAuth.sendPasswordResetEmail(email)
                    .addOnSuccessListener(unused -> {
                        progressDialog.cancel();
                        Toast.makeText(LoginActivity.this, "Email sent", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        progressDialog.cancel();
                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });

        binding.GoToSignUpActivity.setOnClickListener( view -> startActivity(new Intent(LoginActivity.this, MainActivity.class)));
    }
}