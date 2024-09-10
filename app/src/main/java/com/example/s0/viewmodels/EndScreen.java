package com.example.s0.viewmodels;

import android.content.Intent;
import android.os.Bundle;

import android.widget.TextView;

import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

import com.example.s0.R;


public class EndScreen extends AppCompatActivity {
    private TextView rank1TextView;
    private TextView rank2TextView;
    private TextView rank3TextView;
    private TextView rank4TextView;
    private TextView rank5TextView;
    private TextView recentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_main);

        rank1TextView = findViewById(R.id.rank1TextView);
        rank2TextView = findViewById(R.id.rank2TextView);
        rank3TextView = findViewById(R.id.rank3TextView);
        rank4TextView = findViewById(R.id.rank4TextView);
        rank5TextView = findViewById(R.id.rank5TextView);
        recentTextView = findViewById(R.id.recentTextView);

        rank1TextView.setText(Leaderboard.getInstance().getRankText(0));
        rank2TextView.setText(Leaderboard.getInstance().getRankText(1));
        rank3TextView.setText(Leaderboard.getInstance().getRankText(2));
        rank4TextView.setText(Leaderboard.getInstance().getRankText(3));
        rank5TextView.setText(Leaderboard.getInstance().getRankText(4));

        recentTextView.setText(getIntent().getStringExtra("recent"));

        Button resetButton = findViewById(R.id.resetButton);

        // resetting the button clicker
        resetButton.setOnClickListener(v -> {
            Intent reset = new Intent(this, ConfigScreen.class);
            startActivity(reset);
            finish();
        });


    }


}
