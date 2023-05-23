package com.example.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.course_activities.IntroActivity;
import com.example.school_app.R;
import com.google.android.material.card.MaterialCardView;

public class ResultActivity extends AppCompatActivity {

  MaterialCardView home;
  TextView correctt, wrongt, resultInfo, resultScore;
  ImageView resultImage;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_result);

    home = findViewById(R.id.returnHome);
    correctt = findViewById(R.id.correctScore);
    wrongt = findViewById(R.id.wrongScore);
    resultInfo = findViewById(R.id.resultInfo);
    resultScore = findViewById(R.id.resultScore);
    resultImage = findViewById(R.id.resultImage);

    int correct = getIntent().getIntExtra("correct", 0);
    int wrong = getIntent().getIntExtra("wrong", 0);
    correctt.setText("" + correct);
    wrongt.setText("" + wrong);
    resultScore.setText("" + correct);

    if (correct >= 0 && correct <= 2) {
      resultInfo.setText("You have to take the test again");
      resultImage.setImageResource(R.drawable.ic_sad);
    } else if (correct >= 3 && correct <= 4) {
      resultInfo.setText("You have to try a little more");
      resultImage.setImageResource(R.drawable.ic_neutral);
    } else if (correct >= 5) {
      resultInfo.setText("It seems like you have learned a lot!");
      resultImage.setImageResource(R.drawable.ic_smile);
    }

    home.setOnClickListener(
        v -> {
          startActivity(new Intent(ResultActivity.this, IntroActivity.class));
          finish();
        });
  }
}
