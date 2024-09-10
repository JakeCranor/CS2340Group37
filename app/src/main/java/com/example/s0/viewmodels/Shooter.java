package com.example.s0.viewmodels;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import android.graphics.Canvas;
import android.content.Context;

public class Shooter extends View {
    private float x;
    private float y;
    private Paint paint;
    private int radius;

    private boolean shooterIsTrue;

    public Shooter(Context context, float x, float y, int radius) {
        super(context);
        this.x = x;
        this.y = y;
        this.radius = radius;
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        this.shooterIsTrue = true;
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

    public boolean getShooterIsTrue() {
        return shooterIsTrue;
    }
}