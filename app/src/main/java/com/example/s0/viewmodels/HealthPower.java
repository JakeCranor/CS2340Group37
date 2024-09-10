package com.example.s0.viewmodels;

import android.widget.RelativeLayout;
import android.widget.TextView;

public class HealthPower extends PowerDecorator {
    private String description = "Health increased by 100";

    public HealthPower(Powers newPizza) {
        super(newPizza);
    }


    public void setDecription(String newD) {
        description = newD;
    }

    @Override
    public void adjustScore(Score currentScore, TextView scoreTextView) {

    }

    @Override
    public int adjustHealth(int health) {
        return health + 100;
    }

    @Override
    public void removeEnemies(RelativeLayout gameLayout, EnemyOutline enemy1, EnemyOutline enemy2) {
    }
}
