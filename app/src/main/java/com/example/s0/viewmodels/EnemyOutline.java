package com.example.s0.viewmodels;

import android.content.Context;
import android.view.View;

/**
 * absract class for the outlines of the enemies
 */
public abstract class EnemyOutline extends View {
    private String name;
    private double damage;

    public EnemyOutline(Context context) {
        super(context);
    }

    public String getName() {
        return name;
    }

    public void setName(String newname) {
        this.name = newname;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double newdamage) {
        this.damage = newdamage;
    }
    public void movement() {
    }

    public void display() {
    }

    public void updatePosition(float enemyX, float enemyY) {
    }

}
