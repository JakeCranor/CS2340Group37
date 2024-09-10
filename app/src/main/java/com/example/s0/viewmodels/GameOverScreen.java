package com.example.s0.viewmodels;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.s0.R;

public class GameOverScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameover_main);
        Button tryAgainButton = findViewById(R.id.tryAgainButton);
        Button resetLeaderBoardButton = findViewById(R.id.resetLeaderBoardButton);


        //resets the try-again button at the end
        tryAgainButton.setOnClickListener(v -> {
            Intent reset = new Intent(this, ConfigScreen.class);
            startActivity(reset);
            finish();
        });
    }
}
