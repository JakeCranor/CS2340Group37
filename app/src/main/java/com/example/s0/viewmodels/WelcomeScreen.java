package com.example.s0.viewmodels;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.s0.R;

public class WelcomeScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_main);
        Button startBtn = findViewById(R.id.startButton);
        Button exitBtn = findViewById(R.id.exitButton);

        startBtn.setOnClickListener(v -> {
            Intent start = new Intent(this, ConfigScreen.class);
            startActivity(start);
            finish();
        });

        exitBtn.setOnClickListener(v -> {
            System.exit(0);
        });
    }
}
