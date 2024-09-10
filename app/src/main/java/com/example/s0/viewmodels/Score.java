package com.example.s0.viewmodels;

import android.os.CountDownTimer;
import android.widget.TextView;

import java.util.Locale;

public class Score {
    private int score;
    private static CountDownTimer scoreTimer;
    private long timeLeft;
    private static boolean timerRunning;
    public Score (TextView text, long left) {
        timeLeft = left;
        scoreTimer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long l) {
                timeLeft = l;
                updateScore(text);
            }

            @Override
            public void onFinish() {
                timerRunning = false;
            }
        }.start();
        timerRunning = true;

    }
    public void updateScore(TextView text) {
        score = (int) (timeLeft / 1000);
        String scoreDisplay = String.format(Locale.getDefault(), "Score: %d", score);
        text.setText(scoreDisplay);
    }

    public static CountDownTimer getScoreTimer() {
        return scoreTimer;
    }
    public void setDecrease(TextView text) {
        timeLeft = timeLeft - 20000;
        timerRunning = false;
        scoreTimer.cancel();
        scoreTimer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long l) {
                timeLeft = l;
                updateScore(text);
            }

            @Override
            public void onFinish() {
                timerRunning = false;
            }
        }.start();
        timerRunning = true;
    }

    public void setIncrease(TextView text) {
        timeLeft = timeLeft + 100000;
        timerRunning = false;
        scoreTimer.cancel();
        scoreTimer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long l) {
                timeLeft = l;
                updateScore(text);
            }

            @Override
            public void onFinish() {
                timerRunning = false;
            }
        }.start();
        timerRunning = true;
    }

    public static boolean getTimer() {
        return timerRunning;
    }

    public long getTimeLeft () {
        return timeLeft;
    }

    public static void setTimer(boolean x) {
        timerRunning = x;
    }
    public int getScore() {
        return score;
    }
}
