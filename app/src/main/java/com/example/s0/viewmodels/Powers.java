package com.example.s0.viewmodels;

import android.widget.RelativeLayout;
import android.widget.TextView;

public interface Powers{
    String getDescription();

    void adjustScore(Score currentScore, TextView scoreTextView);
    int adjustHealth(int health);
    void removeEnemies(RelativeLayout gameLayout, EnemyOutline enemy1, EnemyOutline enemy2);

}
