package com.example.s0.viewmodels;

import android.widget.RelativeLayout;
import android.widget.TextView;

public class PowerUps implements Powers{
    private float x, y;
    private int radius;
    private boolean isVisible;

    public PowerUps(float x, float y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.isVisible = true;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setInvisible() {
        isVisible = false;
    }

    @Override
    public String getDescription() {
        return "Powers: ";
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

    }
}
