package com.example.school_app;

import java.util.List;

public class Question {

    private final String questionText;
    private final List<Answer> options;


    public Question( String questionText, List<Answer> options) {

        this.questionText = questionText;
        this.options = options;
    }



    public String getQuestionText() {
        return questionText;
    }

    public List<Answer> getOptions() {
        return options;
    }


}
