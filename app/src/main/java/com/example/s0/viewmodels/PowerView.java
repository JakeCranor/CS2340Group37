package com.example.s0.viewmodels;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class PowerView extends View{
        private Paint paint;
        private PowerUps power;

        public PowerView(Context context, PowerUps pow) {
            super(context);
            this.power = pow;
            paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            if (this.power.isVisible()) {
                paint.setColor(Color.WHITE);
                canvas.drawCircle(power.getX(), power.getY(), power.getRadius(), paint);
            }
        }
    }

