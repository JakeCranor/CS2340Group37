package com.example.s0.viewmodels;

import android.content.Context;

public class EnemyFactory {

    public EnemyOutline makeEnemy(double enemyNumber, Context context, float x, float y) {
        EnemyOutline newEnemy = null; //instantiates the new enemy to have a null outline
        if (enemyNumber == 1) {
            return new EnemyOne(context, x, y, 50);
        } else if (enemyNumber == 2) {
            return new EnemyTwo(context, x, y, 75);
        } else if (enemyNumber == 3) {
            return new EnemyThree(context, x, y, 25);
        } else {
            return new EnemyFour(context, x, y, 12);
        }
    }
}
