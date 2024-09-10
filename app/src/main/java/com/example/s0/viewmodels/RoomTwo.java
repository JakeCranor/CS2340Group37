package com.example.s0.viewmodels;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.s0.model.GameActivity;




import com.example.s0.R;
import com.example.s0.views.PlayerView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class RoomTwo extends RoomOne implements Observer, EnemyOneObserver, EnemyTwoObserver {
    // observer pattern design
    private float playerX;
    private float playerY;
    private int character;
    private static int health2 = RoomOne.getHealth();

    private static long timeLeft2 = RoomOne.getTimeLeft();

    private TextView healthTextView;

    protected EnemyOutline enemyOne;
    protected EnemyOutline enemyTwo;
    private float enemyX = 280;
    private float enemyY = 1176;
    private float difY = 20;

    private float enemyXTwo = 285;
    private float enemyYTwo = 1370;

    private PlayerView playerView;
    private TextView scoreTextView;

    private RelativeLayout gameLayout;
    private PlayerMovement player = new PlayerMovement();

    private Score scoreTwo;

    private Powers powerUp;
    private PowerUps power;

    private PowerView newPower;
    private Timer powerTimer;
    private float shooterX;
    private float shooterY;
    private Shooter shooter;
    private Boolean shooterIsTrue = false;
    private int count;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_two);
        gameLayout = findViewById(R.id.gameTwo);
        playerX = updatesX();
        playerY = updatesY();
        character = getIntent().getIntExtra("character", 1);
        playerView = new PlayerView(this, playerX, playerY, 100);
        gameLayout.addView(playerView);
        //factory design pattern
        EnemyFactory factory = new EnemyFactory();
        enemyOne = factory.makeEnemy(1, this, enemyX, enemyY);
        enemyTwo = factory.makeEnemy(2, this, enemyXTwo, enemyYTwo);
        gameLayout.addView(enemyOne);
        gameLayout.addView(enemyTwo);
        healthTextView = findViewById(R.id.HealthTextView);
        healthTextView.setText(" Health: " + this.health2);
        healthTextView.bringToFront();
        scoreTextView = findViewById(R.id.scoreTextView);
        scoreTwo = new Score(scoreTextView, timeLeft2);
        scoreTextView.bringToFront();

        intializePower();
        drawPower();

        powerTimer = new Timer();
        powerTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        checkPowerCollisions();
                    }
                });
            }
        }, 0, 500);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        float y = playerY + 20;
        float x = playerX + 20;
        if (!(((y > 200 && y < 1030) && (x > 540 && x < 1000))
                || ((y > 1190 && y < 1490) && (x > 370 && x < 1070))
                || ((y > 1550 && y < 2400) && (x > 540 && x < 980)))) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_DPAD_LEFT:
                    playerX = player.moveLeft(playerX);
                    break;
                case KeyEvent.KEYCODE_DPAD_RIGHT:
                    playerX = player.moveRight(playerX);
                    break;
                case KeyEvent.KEYCODE_DPAD_UP:
                    playerY = player.moveUp(playerY);
                    break;
                case KeyEvent.KEYCODE_DPAD_DOWN:
                    playerY = player.moveDown(playerY);
                    break;
                case KeyEvent.KEYCODE_SPACE:
                    if (shooterIsTrue) {
                        gameLayout.removeView(shooter);
                        shooter.setX(20000);
                        shooter.setY(20000);
                        shooterX = 20000;
                        shooterY = 20000;
                    }
                    shooterX = playerX;
                    shooterY = playerY;
                    shooter = new Shooter(this, shooterX, shooterY, 15);
                    gameLayout.addView(shooter);
                    shooterIsTrue = true;
                    count = 0;
                default:
                    //no action
                    break;
            }
        } else {
            switch (keyCode) {
                case KeyEvent.KEYCODE_DPAD_LEFT:
                    playerX = player.moveRight(playerX);
                    playerX = player.moveRight(playerX);
                    break;
                case KeyEvent.KEYCODE_DPAD_RIGHT:
                    playerX = player.moveLeft(playerX);
                    playerX = player.moveLeft(playerX);
                    break;
                case KeyEvent.KEYCODE_DPAD_UP:
                    playerY = player.moveDown(playerY);
                    playerY = player.moveDown(playerY);
                    break;
                case KeyEvent.KEYCODE_DPAD_DOWN:
                    playerY = player.moveUp(playerY);
                    playerY = player.moveUp(playerY);
                    break;
                default:
                    //no action
                    break;
            }
        }
        if (shooterIsTrue && count < 4) {
            shooterY = shooterY + 100;
            shooter.updatePosition(shooterX, shooterY);
            count++;
        }
        if (count >= 5) {
            gameLayout.removeView(shooter);
            shooter.setX(20000);
            shooter.setY(20000);
            shooterX = 20000;
            shooterY = 20000;
        }
        playerView.updatePosition(playerX, playerY);

        int randomx = new Random().nextInt(385) + 280;
        enemyX = randomx;
        enemyOne.updatePosition(enemyX, enemyY);

        if (enemyYTwo + difY > 1600) {
            difY = -20;
        } else if (enemyYTwo + difY < 1370) {
            difY = 20;
        }
        enemyYTwo = enemyYTwo + difY;
        enemyTwo.updatePosition(enemyXTwo, enemyYTwo);
        if (collisionDetectedEnemy1(playerX, playerY, enemyX, enemyY)) {
            health2 -= 14;
            healthTextView.setText(" Health: " + this.health2);
            scoreTwo.setDecrease(scoreTextView);
        }
        if (collisionDetectedEnemy2(playerX, playerY, enemyXTwo, enemyYTwo)) {
            health2 -= 20;
            healthTextView.setText(" Health: " + this.health2);
            scoreTwo.setDecrease(scoreTextView);
        }
        if (shooterIsTrue) {
            if (collisionDetectedEnemy3(shooterX, shooterY, enemyX, enemyY)) {
                gameLayout.removeView(enemyOne);
                enemyY = 20000;
                enemyX = 20000;
                gameLayout.removeView(shooter);
                shooter.setX(20000);
                shooter.setY(20000);
                shooterX = 20000;
                shooterY = 20000;
            }
            if (collisionDetectedEnemy4(shooterX, shooterY, enemyXTwo, enemyYTwo)) {
                gameLayout.removeView(enemyTwo);
                enemyXTwo = 20000;
                enemyYTwo = 20000;
                gameLayout.removeView(shooter);
                shooter.setX(20000);
                shooter.setY(20000);
                shooterX = 20000;
                shooterY = 20000;
            }
        }
        if (playerY >= 2270 && playerY <= 2310 && playerX >= 350 && playerX <= 400) {
            RoomThree.setHealth(health2);
            timeLeft2 = scoreTwo.getTimeLeft();
            RoomThree.setTimeLeft(timeLeft2);
            Intent start = new Intent(this, RoomThree.class);
            start.putExtra("recent", getIntent().getStringExtra("recent"));
            startActivity(start);
            finish();
        }
        if (health2 <= 0) {
            Intent start = new Intent(this, GameOverScreen.class);
            startActivity(start);
            finish();
        }
        return true;
    }

    private void intializePower() {
        int radius = 50;
        //this.powerUp = new ScorePower();
        this.power = new PowerUps(1000, 550, radius);
        this.powerUp = new HealthPower(this.power);
    }
    private void drawPower() {
        this.newPower = new PowerView(this, this.power);
        gameLayout.addView(newPower);
        newPower.bringToFront();
    }

    private void checkPowerCollisions() {

        if (this.power.isVisible() && isCollision(playerView, this.power)) {
            this.power.setInvisible();
            gameLayout.removeView(this.newPower);
            health2 = this.powerUp.adjustHealth(health2);
            healthTextView.setText(" Health: " + this.health2);
        }
    }

    private boolean isCollision(PlayerView playerView, PowerUps pow) {
        float playerX = playerView.getX();
        float playerY = playerView.getY();
        int playerRadius = playerView.getRadius();
        float dotX = pow.getX();
        float dotY = pow.getY();
        int dotRadius = pow.getRadius();
        RectF playerRect = new RectF(playerX - playerRadius, playerY - playerRadius, playerX + playerRadius, playerY + playerRadius);
        RectF dotRect = new RectF(dotX - dotRadius, dotY - dotRadius, dotX + dotRadius, dotY + dotRadius);
        return playerRect.intersect(dotRect);
    }
    public boolean collisionDetectedEnemy1(float px, float py, float ex, float ey) {
        return Math.abs(px - ex) < 100
                && Math.abs(py - ey) < 100;
    }

    public boolean collisionDetectedEnemy2(float px, float py, float ex, float ey) {
        return Math.abs(px - ex) < 125
                && Math.abs(py - ey) < 125;
    }
    @Override
    public float updatesX() {
        return 400;
    }
    public float updatesE1X() {
        return enemyX; }
    public float updatesE1Y() {
        return enemyY; }
    public float updatesE2X() {
        return enemyXTwo; }
    public float updatesE2Y() {
        return enemyYTwo; }

    @Override
    public float updatesY() {
        return 400;
    }

    public float getEnemyOneX() {
        return enemyX;
    }

    public float getEnemyOneY() {
        return enemyY;
    }

    public float getEnemyTwoX() {
        return enemyXTwo;
    }

    public float getEnemyTwoY() {
        return enemyYTwo;
    }

    public static int getHealth() {
        return health2;
    }

    public static long getTimeleft() {
        return timeLeft2;
    }
    public static void setHealth(int x) {
        health2 = x;
    }

    public static void setTimeLeft(long x) {
        timeLeft2 = x;
    }
}