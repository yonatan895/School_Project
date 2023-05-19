package com.example.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.example.classes.QuizActivity;
import com.example.models.Question;
import com.example.school_app.R;

import java.util.ArrayList;
import java.util.Collections;

public class QuizLauncher extends QuizActivity {
    TextView question,ans_a, ans_b, ans_c, ans_d;

    int correct = 0;
    ArrayList<Question> questions;

    final int QUESTIONS_LENGTH = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_launcher);
        String quizName = getIntent().getStringExtra("quizName");
        questions = setQuestionsList(quizName);
        question = findViewById(R.id.question);
        ans_a = findViewById(R.id.ans_a);
        ans_b = findViewById(R.id.ans_b);
        ans_c = findViewById(R.id.ans_c);
        ans_d = findViewById(R.id.ans_d);
        TextView [] answerTextViews = {ans_a, ans_b, ans_c, ans_d};

        Collections.shuffle(questions);
        for (int i = 0; i < QUESTIONS_LENGTH; i++) {
            setQuestionScreen(i, question, answerTextViews, questions);
            for (TextView answer: answerTextViews) {
                int finalI = i;
                int finalI1 = i;
                answer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Question question_ref = questions.get(finalI);
                        if (question_ref.getCorrectAnswer(question_ref.getAnswers()) == answer.getText()) {
                            correct++;
                            answer.setBackgroundResource(R.color.green);

                        } else {
                            answer.setBackgroundResource(R.color.red);
                            answer.setTextColor(getResources().getColor(R.color.white));
                        }

                        if (finalI1 < questions.size() - 1 ) {
                            Handler handler = new Handler();
                            handler.postDelayed(() -> {
                                answer.setBackgroundResource(R.color.white);
                                answer.setTextColor(getResources().getColor(R.color.white));
                            }, 500);
                        } else {
                            updateScore(correct);
                            Intent intent = new Intent(QuizLauncher.this, ResultActivity.class);
                            intent.putExtra("correct", correct);
                            intent.putExtra("wrong", QUESTIONS_LENGTH - correct);
                            startActivity(intent);
                            finish();

                        }
                    }
                });
                /*
                answer.setOnClickListener(v -> {
                    Question question_ref = questions.get(finalI);
                    if (question_ref.getCorrectAnswer(question_ref.getAnswers()) == answer.getText()) {
                        correct++;
                        answer.setBackgroundResource(R.color.green);

                    } else {
                        answer.setBackgroundResource(R.color.red);
                        answer.setTextColor(getResources().getColor(R.color.white));
                    }

                    if (finalI1 < questions.size() - 1 ) {
                        Handler handler = new Handler();
                        handler.postDelayed(() -> {
                            answer.setBackgroundResource(R.color.white);
                            answer.setTextColor(getResources().getColor(R.color.text_secondary_color));
                        }, 500);
                    } else {
                        updateScore(correct);
                        Intent intent = new Intent(QuizLauncher.this, ResultActivity.class);
                        intent.putExtra("correct", correct);
                        intent.putExtra("wrong", QUESTIONS_LENGTH - correct);
                        startActivity(intent);
                        finish();

                    }
                });

                 */
            }



        }

    }
}