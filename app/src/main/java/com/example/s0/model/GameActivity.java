package com.example.s0.model;
import android.annotation.SuppressLint;
import android.content.Intent;

import android.os.Bundle;

import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.widget.Button;

import android.widget.TextView;

import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.s0.R;

import com.example.s0.viewmodels.Leaderboard;
import com.example.s0.viewmodels.PlayerRecord;
import com.example.s0.viewmodels.RoomOne;
import com.example.s0.viewmodels.RoomThree;
import com.example.s0.viewmodels.Score;

import com.example.s0.views.PlayerView;

import java.util.Locale;
import java.util.Random;



public class GameActivity extends AppCompatActivity {

    private double difficulty;
    private static int startingHealth;
    private static String name;
    private int character;
    private TextView nameTextView;
    private TextView difficultyTextView;
    private TextView startingHealthTextView;
    private PlayerView playerView;
    private ImageView playerImageView;
    private TextView scoreTextView;
    private float playerX;
    private float playerY;
    private Random random = new Random();
    private static PlayerRecord record = null;
    private ConstraintLayout gameLayout;
    private int screenWidth;
    private int screenHeight;
    public static Score score;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameLayout = findViewById(R.id.game);
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        // Spawn player in middle of screen
        playerX = screenWidth / 2;
        playerY = screenHeight / 2;
        //record = new PlayerRecord(score, name, "time");
        name = getIntent().getStringExtra("name");
        difficulty = getIntent().getDoubleExtra("difficulty", 1);
        character = getIntent().getIntExtra("character", 1);
        startingHealth = health(difficulty);
        playerView = new PlayerView(this, playerX, playerY, character);
        gameLayout.addView(playerView);
        nameTextView = findViewById(R.id.nameTextView);
        difficultyTextView = findViewById(R.id.difficultyTextView);
        startingHealthTextView = findViewById(R.id.startingHealthTextView);
        playerImageView = findViewById(R.id.playerImageView);
        scoreTextView = findViewById(R.id.scoreTextView);
        nameTextView.setText("Name: " + name);
        difficultyTextView.setText("Difficulty: " + difficulty);
        startingHealthTextView.setText("Starting Health: " + startingHealth);
        score = new Score (scoreTextView, 500000);
        Leaderboard board = new Leaderboard();
        playerImageView.setImageResource(R.drawable.redastronaut);
        if (character == 2) {
            playerImageView.setImageResource(R.drawable.blueastronaut);
        } else if (character == 3) {
            playerImageView.setImageResource(R.drawable.blackandwhiteastronaut);
        }
        Button exitBtn = findViewById(R.id.endButton);

        exitBtn.setOnClickListener(v -> {
            RoomOne.setHealth(startingHealth);
            RoomThree.setBigName(name);
            Intent end = new Intent(GameActivity.this, RoomOne.class);
            startActivity(end);
            finish();
        });
    }



    public boolean collisionDetectedEnemy1(int px, int py, int ex, int ey) {
        return Math.abs(px - ex) < 100
                && Math.abs(py - ey) < 100;
    }
    public boolean collisionDetectedEnemy2(int px, int py, int ex, int ey) {
        return Math.abs(px - ex) < 125
                && Math.abs(py - ey) < 125;
    }
    public boolean collisionDetectedEnemy3(int px, int py, int ex, int ey) {
        return Math.abs(px - ex) < 75
                && Math.abs(py - ey) < 75;
    }
    public boolean collisionDetectedEnemy4(int px, int py, int ex, int ey) {
        return Math.abs(px - ex) < 62
                && Math.abs(py - ey) < 62;
    }

    public static String getName() {
        return name;
    }

    public static int health(double diff) {
        return (int) (300 / diff);
    }

    public static int getHealth() {
        return startingHealth;
    }
    public static PlayerRecord getRecord() {
        return record;
    }
    public static void setRecord(PlayerRecord x) {
        record = x;
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
        case KeyEvent.KEYCODE_DPAD_LEFT:
            playerX -= 50;
            break;
        case KeyEvent.KEYCODE_DPAD_RIGHT:
            playerX += 50;
            break;
        case KeyEvent.KEYCODE_DPAD_UP:
            playerY -= 50;
            break;
        case KeyEvent.KEYCODE_DPAD_DOWN:
            playerY += 50;
            break;
        default:
            // no action
            break;
        }
        playerView.updatePosition(playerX, playerY);
        return true;
    }


}
