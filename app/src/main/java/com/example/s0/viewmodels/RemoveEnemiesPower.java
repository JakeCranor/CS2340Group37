package com.example.s0.viewmodels;

import android.widget.RelativeLayout;
import android.widget.TextView;

public class RemoveEnemiesPower extends PowerDecorator{
    private String description = "Enemies removed";

    public RemoveEnemiesPower(Powers newPizza) {
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
        return 0;
    }

    @Override
    public void removeEnemies(RelativeLayout gameLayout, EnemyOutline enemy1, EnemyOutline enemy2) {
        gameLayout.removeView(enemy1);
        gameLayout.removeView(enemy2);
    }

}
