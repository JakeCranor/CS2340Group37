package com.example.s0.viewmodels;

import android.widget.RelativeLayout;
import android.widget.TextView;

public class ScorePower extends PowerDecorator{
    private String description = "Score increased by 100";

    public ScorePower(Powers newPower) {
        super(newPower);
    }
    public void adjustScore(Score currentScore, TextView scoreTextView) {
        currentScore.setIncrease(scoreTextView);
    }

    @Override
    public int adjustHealth(int health) {
        return 0;
    }

    @Override
    public void removeEnemies(RelativeLayout gameLayout, EnemyOutline enemy1, EnemyOutline enemy2) {

    }

    public void setDecription(String newD) {
        description = newD;
    }

}
