package com.example.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import com.example.classes.QuizActivity;
import com.example.models.Question;
import com.example.school_app.R;
import com.example.views.CountDownTimerView;

import java.util.ArrayList;
import java.util.Collections;

public class QuizLauncher extends QuizActivity {
    TextView question,ans_a, ans_b, ans_c, ans_d;
    TextView [] answerTextViews;

    int correct = 0;

    private int currentQuestionIndex = 0;
    ArrayList<Question> questions;


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
       answerTextViews = new TextView[]{ans_a, ans_b, ans_c, ans_d};

        Collections.shuffle(questions);
        setQuestionScreen(currentQuestionIndex, question, answerTextViews, questions);
        setAnswerClickListeners();

        startCountDownTimer(currentQuestionIndex);

    }

    /**
     * The setAnswerClickListeners function sets the onClickListener for each answer TextView.
     * When an answer is clicked, it checks if the text of that TextView matches the correctAnswer string in its corresponding Question object.
     * If it does match, then we increment our correct variable by 1 and set a green background color to that TextView.
     * Otherwise, we set a red background color to that TextView and change its textColor to white so it's visible against the red background.
     *
     */
    void setAnswerClickListeners() {
        for (TextView answer : answerTextViews) {
            // Disable all answer TextViews
            answer.setOnClickListener(v -> {
                for (TextView ans: answerTextViews) {
                    ans.setClickable(false);
                }
                Question questionRef = questions.get(currentQuestionIndex);
                if (questionRef.getCorrectAnswer(questionRef.getAnswers()).contentEquals(answer.getText())) {
                    correct++;
                    answer.setBackgroundResource(R.color.green);
                } else {
                    answer.setBackgroundResource(R.color.red);
                    answer.setTextColor(getResources().getColor(R.color.white));
                }

                // Adding a 500ms delay after answering the question, before moving to the next
                Handler handler = new Handler();
                handler.postDelayed(() -> {
                    answer.setBackgroundResource(R.color.white);
                    answer.setTextColor(getResources().getColor(R.color.text_secondary_color));
                    moveToNextQuestion(currentQuestionIndex);
                    currentQuestionIndex++;
                    // Re-enable all TextViews
                    for (TextView ans: answerTextViews) {
                        ans.setClickable(true);
                    }
                }, 500);
            });
        }
    }



    /**
     * The moveToNextQuestion function is called when the user clicks on a button.
     * It checks if there are more questions to be answered, and if so, it sets the next question screen.
     * If not, it updates the score and starts an intent to go to ResultActivity.java

     *
     * @param currentQuestionIndex Get the current question from the arraylist of questions
     *
     *
     */
    void moveToNextQuestion(int currentQuestionIndex) {
        if (currentQuestionIndex < questions.size() - 1 ) {
            setQuestionScreen(currentQuestionIndex + 1, question, answerTextViews, questions);
            CountDownTimerView countDownTimerView = findViewById(R.id.countDownTimerView);
            countDownTimerView.cancelTimer();
            startCountDownTimer(currentQuestionIndex + 1);
        } else {
            updateScore(correct);
            Intent intent = new Intent(QuizLauncher.this, ResultActivity.class);
            intent.putExtra("correct", correct);
            intent.putExtra("wrong", (currentQuestionIndex + 1) - correct);
            startActivity(intent);
            finish();
        }
    }



    /**
     * The startCountDownTimer function is used to start the countdown timer for each question.
     * The function takes in an integer parameter, which represents the index of the current question.
     * The function then sets up a CountDownTimerView object and starts it with a duration of 60 seconds (60000 milliseconds).
     * When this timer finishes, we want to move on to the next question by calling our moveToNextQuestion method.

     *
     * @param questionIndex Pass the question index to the movetonextquestion function
     *
     *
     */
    void startCountDownTimer(final int questionIndex) {
        CountDownTimerView countdownTimerView = findViewById(R.id.countDownTimerView);
        countdownTimerView.setTimerDuration(60000);
        countdownTimerView.startTimer();

        countdownTimerView.setOnTimerFinishedListener(() -> runOnUiThread(() -> moveToNextQuestion(questionIndex)));
    }


}