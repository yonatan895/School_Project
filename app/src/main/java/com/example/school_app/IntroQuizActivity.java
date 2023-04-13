package com.example.school_app;


import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.ArrayList;

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
