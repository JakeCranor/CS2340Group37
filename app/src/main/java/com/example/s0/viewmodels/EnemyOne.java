package com.example.s0.viewmodels;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class EnemyOne extends EnemyOutline {
    private float x; // horizontal position of the enemy
    private float y; // vertical position of the enemy
    private int character; // integer representing the character type of the enemy
    private Paint paint; // the color of the enemy
    private int radius; // the radius, repesenting the size of the enemy

    /**
     * Constructor for the EnemyOne object
     * @param context Context of the EnemyOutline
     * @param x Starting x coordinate of the enemy
     * @param y Starting y coordinate of the enemy
     * @param radius Radius of the enemy
     */
    public EnemyOne(Context context, float x, float y, int radius) {
        super(context);
        this.x = x;
        this.y = y;
        this.radius = radius;
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        setName("One");
        setDamage(14.0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(x, y, radius, paint);
    }

    public void updatePosition(float newX, float newY) {
        x = newX;
        y = newY;
        invalidate();
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

}