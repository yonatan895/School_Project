package com.example.school_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;


public class IntroQuizActivity extends QuizActivity {
    private final int correctAnswers = 0;

    private TextView mQuestionTextView;
    private RadioGroup mAnswersRadioGroup;

    private final ArrayList<Question> mQuestionsList = new ArrayList<>();
    private int mCurrentQuestionIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mQuestionTextView = findViewById(R.id.question_text_view);
        mAnswersRadioGroup = findViewById(R.id.answers_radio_group);
        Button mSubmitButton = findViewById(R.id.submit_button);

        loadQuiz(getCollectionName());

        mSubmitButton.setOnClickListener(view -> onAnswerSelected());





    }



    @Override
    protected String getCollectionName() {
        return "Introduction";
    }
}