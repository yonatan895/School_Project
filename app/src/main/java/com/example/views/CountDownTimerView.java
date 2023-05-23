package com.example.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.View;

public class CountDownTimerView extends View {

    private long timerDuration;
    private long timeRemaining;
    private CountDownTimer countDownTimer;
    private OnTimerFinishedListener onTimerFinishedListener;

    private Paint textPaint;

    public CountDownTimerView(Context context) {
        super(context);
        init();
    }

    public CountDownTimerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CountDownTimerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * The init function initializes the textPaint object, which is used to draw
     * the text on the canvas. It sets its color to black and its size to 40 pixels.
     *
     */
    private void init() {
        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(40);
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    public void setTimerDuration(long durationInMillis) {
        this.timerDuration = durationInMillis;
        this.timeRemaining = durationInMillis;
    }

    public void startTimer() {
        countDownTimer = new CountDownTimer(timerDuration, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeRemaining = millisUntilFinished;
                invalidate();
            }

            @Override
            public void onFinish() {
                timeRemaining = 0;
                invalidate();
                if (onTimerFinishedListener != null) {
                    onTimerFinishedListener.onTimerFinished();
                }
            }
        };
        countDownTimer.start();
    }


    public void setOnTimerFinishedListener(OnTimerFinishedListener listener) {
        this.onTimerFinishedListener = listener;
    }

    public void cancelTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            timeRemaining = timerDuration;
            invalidate();
        }
    }

    /**
     * The onDraw function is called whenever the view needs to be redrawn.
     * This happens when the view is first displayed, and then again every time it's invalidated.
     * The Canvas parameter passed in represents a drawing surface that you can use to draw on.

     *
     * @param canvas Draw on the screen
     *
     *
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        long secondsRemaining = timeRemaining / 1000;
        String timeText = String.format("%02d:%02d", secondsRemaining / 60, secondsRemaining % 60);

        float textX = getWidth() / 2f;
        float textY = getHeight() / 2f;

        canvas.drawText(timeText, textX, textY, textPaint);
    }

    public interface OnTimerFinishedListener {
        void onTimerFinished();
    }
}