package com.example.school_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.school_app.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    ProgressDialog progressDialog;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);
        setContentView(binding.getRoot());
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();

        progressDialog=new ProgressDialog(this);

        binding.signup.setOnClickListener(view -> {
            String email=binding.email.getText().toString();
            String password=binding.password.getText().toString();


            progressDialog.show();
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener(authResult -> {
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        progressDialog.cancel();

                        firebaseFirestore.collection("User")
                                .document(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()))
                                .set(new UserModel(email, 0, 0));
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.cancel();

                    });


        });

        binding.GoToLoginActivity.setOnClickListener( view -> startActivity(new Intent(MainActivity.this, LoginActivity.class)));

    }
}