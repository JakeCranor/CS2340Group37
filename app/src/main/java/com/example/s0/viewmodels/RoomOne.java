package com.example.s0.viewmodels;
import android.annotation.SuppressLint;

import android.content.Intent;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;


import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.s0.R;
import com.example.s0.model.GameActivity;
import com.example.s0.views.PlayerView;

import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class RoomOne extends GameActivity implements Observer, EnemyThreeObserver,
        EnemyFourObserver {
    // observer pattern design
    private float playerX;
    private float playerY;
    private TextView healthTextView;

    private TextView scoreTextView;

    private boolean done = false;

    private float enemyX = 250;
    private float enemyY = 550;

    private float enemyXTwo = 550;
    private float enemyYTwo = 1050;
    private int count;
    private int character;

    private Random random = new Random();

    protected PlayerView playerView;
    private static int health1 = GameActivity.getHealth();
    protected EnemyOutline enemyOne;
    protected EnemyOutline enemyTwo;

    private PlayerMovement player = new PlayerMovement();

    private RelativeLayout gameLayout;
    private Score scoreOne;

    private static long timeLeft1;
    private Powers powerUp;
    private PowerUps power;

    private PowerView newPower;
    private Timer powerTimer;
    private float shooterX;
    private float shooterY;
    private Shooter shooter;
    private Boolean shooterIsTrue = false;


    private CountDownTimer countDownTimer;
    private TextView timerTextView;
    private long totalTimeInMillis = 60000; // 1 minute in milliseconds
    private long intervalInMillis = 1000; // 1 second in milliseconds

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_one);
        gameLayout = findViewById(R.id.gameOne);
        playerX = updatesX();
        playerY = updatesY();
        enemyX = updatesE3X();
        enemyY = updatesE3Y();
        enemyXTwo = updatesE4X();
        enemyYTwo = updatesE4Y();
        character = getIntent().getIntExtra("character", 1);
        playerView = new PlayerView(this, playerX, playerY, 100);
        gameLayout.addView(playerView);
        //factory design pattern
        EnemyFactory factory = new EnemyFactory();
        enemyOne = factory.makeEnemy(3, this, enemyX, enemyY);
        enemyTwo = factory.makeEnemy(4, this, enemyXTwo, enemyYTwo);
        gameLayout.addView(enemyOne);
        gameLayout.addView(enemyTwo);
        healthTextView = findViewById(R.id.HealthTextView);
        healthTextView.setText(" Health: " + this.health1);
        healthTextView.bringToFront();
        scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.bringToFront();
        scoreOne = new Score(scoreTextView, 500000);
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
        timerTextView = findViewById(R.id.timerTextView);

        // Start the timer
        startTimer();
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(totalTimeInMillis, intervalInMillis) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Update the UI with the remaining time
                updateTimerText(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                // Handle the timer finish event
                timerTextView.setText("Game Over!");
            }
        };

        // Start the timer
        countDownTimer.start();
    }

    private void updateTimerText(long millisUntilFinished) {
        // Convert milliseconds to minutes and seconds
        long minutes = millisUntilFinished / 60000;
        long seconds = (millisUntilFinished % 60000) / 1000;

        // Format the time and update the TextView
        String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);
        timerTextView.setText(timeLeftFormatted);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Stop the timer when the activity is destroyed to prevent memory leaks
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        float y = playerY + 20;
        float x = playerX + 20;
        if (!(((y > 560 && y < 1000) && (x > 350 && x < 1300))
                || ((y > 1750 && y < 2220) && (x > 340 && x < 1140))
                || ((y > 1160 && y < 1580) && (x > 520 && x < 950)))) {

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
        enemyX = player.moveRight(enemyX);
        enemyOne.updatePosition(enemyX, enemyY);
        if (collisionDetectedEnemy3(playerX, playerY, enemyX, enemyY)) {
            health1 -= 10;
            healthTextView.setText(" Health: " + this.health1);
            scoreOne.setDecrease(scoreTextView);
        }
        if (collisionDetectedEnemy4(playerX, playerY, enemyXTwo, enemyYTwo)) {
            health1 -= 25;
            healthTextView.setText(" Health: " + this.health1);
            scoreOne.setDecrease(scoreTextView);
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

        if (playerY >= 1310 && playerY <= 1360 && playerX >= 1090 && playerX <= 1190) {
            done = true;
            RoomTwo.setHealth(health1);
            timeLeft1 = scoreOne.getTimeLeft();
            RoomTwo.setTimeLeft(timeLeft1);
            Intent start = new Intent(this, RoomTwo.class);
            start.putExtra("recent", getIntent().getStringExtra("recent"));
            startActivity(start);
            finish();
        }
        if (health1 <= 0) {
            Intent start = new Intent(this, GameOverScreen.class);
            startActivity(start);
            finish();
        }
        return true;
    }

    // infinie loop to change the position of the enemy
    public void moveEnemyOne() {
        while (!done) {
            float x = enemyX + 20;
            float y = enemyY + 20;
            enemyX = player.moveRight(enemyX);
            enemyOne.updatePosition(enemyX, enemyY);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void intializePower() {
            int radius = 50;
            //this.powerUp = new ScorePower();
            this.power = new PowerUps(1000, 550, radius);
            this.powerUp = new ScorePower(this.power);
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
                powerUp.adjustScore(scoreOne, scoreTextView);
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



    public boolean collisionDetectedEnemy3(float px, float py, float ex, float ey) {
        return Math.abs(px - ex) < 75
                && Math.abs(py - ey) < 75;
    }

    public boolean collisionDetectedEnemy4(float px, float py, float ex, float ey) {
        return Math.abs(px - ex) < 62
                && Math.abs(py - ey) < 62;
    }

    public static void doDisplay(EnemyOutline anEnemy) {
        anEnemy.display();
    }

    @Override
    public float updatesX() {
        return 400;
    }
    public float updatesE3X() {
        return enemyX; }
    public float updatesE3Y() {
        return enemyY; }
    public float updatesE4X() {
        return enemyXTwo; }
    public float updatesE4Y() {
        return enemyYTwo; }

    @Override
    public float updatesY() {
        return 400;
    }

    public float getPlayerX() {
        return playerX;
    }

    public float getPlayerY() {
        return playerY;
    }

    public void setPlayerX(float x) {
        playerX = x;
    }

    public void setPlayerY(float y) {
        playerY = y;
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
        return health1;
    }

    public static void setHealth(int x) {
        health1 = x;
    }

    public static long getTimeLeft() {
        return timeLeft1;
    }

    public static void setTimeLeft1(int x) {
        timeLeft1 = x;
    }

    public void setShooterX(float x) {
        shooterX = x;
    }

    public void setShooterY(float y) {
        shooterY = y;
    }

    public float getShooterX() {
        return shooterX;
    }

    public float getShooterY() {
        return shooterY;
    }

    public ViewGroup getGameLayout() {
        return gameLayout;
    }

    public int getScore() {
        return scoreOne.getScore();
    }
}