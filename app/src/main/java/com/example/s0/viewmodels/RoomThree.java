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

import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class RoomThree extends RoomTwo implements Observer, EnemyTwoObserver, EnemyOneObserver {
    // observer pattern design
    private float playerX;
    private float playerY;
    private int character;
    private TextView healthTextView;
    private static int health3 = RoomTwo.getHealth();
    private static long timeLeft3 = RoomTwo.getTimeleft();
    private TextView scoreTextView;
    protected EnemyOutline enemyOne;
    protected EnemyOutline enemyTwo;
    private float enemyX = 293;
    private float enemyY = 916;
    private float difX = 20;
    private float difY = 20;

    private float enemyXTwo = 329;
    private float enemyYTwo = 1610;

    private static String bigname = GameActivity.getName();

    private PlayerView playerView;

    private RelativeLayout gameLayout;
    private PlayerMovement player = new PlayerMovement();

    private Date playDate = new Date();

    private Score scoreThree;

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
        health3 = RoomTwo.getHealth();
        setContentView(R.layout.room_three);
        gameLayout = findViewById(R.id.gameThree);
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
        healthTextView.setText(" Health: " + this.health3);
        healthTextView.bringToFront();
        scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.bringToFront();
        scoreThree = new Score(scoreTextView, timeLeft3);
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
        if (!(((y > 200 && y < 500) && ((x > 200 && x < 470) || (x > 980 && x < 2500)))
                || ((y > 900 && y < 1700) && (x > 535 && x < 940))
                || ((y > 2120 && y < 2500) && ((x > 200 && x < 470) || (x > 980 && x < 2500))))) {
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

        int randomx = new Random().nextInt(267) + 293;
        enemyX = randomx;
        enemyOne.updatePosition(enemyX, enemyY);

        if (enemyXTwo + difX > 1168) {
            difX = -20;
        } else if (enemyXTwo + difX < 329) {
            difX = 20;
        }
        enemyXTwo = enemyXTwo + difX;

        if (enemyYTwo + difY > 2000) {
            difY = -20;
        } else if (enemyYTwo + difY < 1610) {
            difY = 20;
        }
        enemyYTwo = enemyYTwo + difY;
        enemyTwo.updatePosition(enemyXTwo, enemyYTwo);
        if (collisionDetectedEnemy1(playerX, playerY, enemyX, enemyY)) {
            health3 -= 14;
            healthTextView.setText(" Health: " + this.health3);
            scoreThree.setDecrease(scoreTextView);
        }
        if (collisionDetectedEnemy2(playerX, playerY, enemyXTwo, enemyYTwo)) {
            health3 -= 20;
            healthTextView.setText(" Health: " + this.health3);
            scoreThree.setDecrease(scoreTextView);
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
        if (playerY >= 1300 && playerY <= 1350 && playerX >= 200 && playerX <= 450) {
            PlayerRecord player3 = new PlayerRecord((int)scoreThree.getTimeLeft(), bigname, playDate.toString());
            GameActivity.setRecord(player3);
            Intent start = new Intent(this, EndScreen.class);
            start.putExtra("recent", getIntent().getStringExtra("recent"));
            start.putExtra("recent", "Recent: " + bigname + " - " + (int)scoreThree.getTimeLeft() + " - "
                    + playDate.toString());
            Leaderboard.getInstance().addRank(getRecord());
            startActivity(start);
            finish();
        }
        if (health3 <= 0) {
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
        this.powerUp = new RemoveEnemiesPower(this.power);
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
            this.powerUp.removeEnemies(gameLayout, enemyOne, enemyTwo);
            enemyY = 20000;
            enemyX = 20000;
            enemyXTwo = 20000;
            enemyYTwo = 20000;
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
    public static String getBigName() {
        return bigname;
    }

    @Override
    public float updatesX() {
        return 500;
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
        return 500;
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
        return health3;
    }
    public static void setHealth(int x) {
        health3 = x;
    }

    public static void setBigName(String x) {
        bigname = x;
    }

    public static void setTimeLeft(long x) {
        timeLeft3 = x;
    }
}