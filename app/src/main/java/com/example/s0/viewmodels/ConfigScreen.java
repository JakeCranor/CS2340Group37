package com.example.s0.viewmodels;

import androidx.appcompat.app.AppCompatActivity;



import android.content.Intent;

import android.os.Bundle;

import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import android.widget.RadioGroup;

import com.example.s0.R;
import com.example.s0.model.GameActivity;


public class ConfigScreen extends AppCompatActivity {
    private double difficulty;
    private static String name;
    private TextView editText;
    private int character;
    private boolean enteredNameCorrectly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startBtn = findViewById(R.id.startButton);
        TextView nameInput = findViewById(R.id.editText);
        TextView error = findViewById(R.id.error);

        // Set difficulty based on difficulty checked
        startBtn.setOnClickListener(v -> {
            RadioGroup difficultyRadioGroup = findViewById(R.id.difficultyRadioGroup);
            RadioGroup characterRadioGroup = findViewById(R.id.characterRadioGroup);
            difficulty = 1.0;
            name = nameInput.getText().toString();
            enteredNameCorrectly = getName(name);
            if (!enteredNameCorrectly) {
                error.setVisibility(View.VISIBLE);
            } else {
                switch (difficultyRadioGroup.getCheckedRadioButtonId()) {
                case R.id.radioEasy:
                    difficulty = 0.5;
                    break;
                case R.id.radioMedium:
                    difficulty = 0.75;
                    break;
                case R.id.radioHard:
                    difficulty = 1;
                    break;
                default:
                    difficulty = 0.5;
                    break;
                }
                switch (characterRadioGroup.getCheckedRadioButtonId()) {
                case R.id.radioCharacter1:
                    character = 1;
                    break;
                case R.id.radioCharacter2:
                    character = 2;
                    break;
                case R.id.radioCharacter3:
                    character = 3;
                    break;
                default:
                    character = 1;
                    break;
                }
                Intent game = new Intent(this, GameActivity.class);
                game.putExtra("difficulty", difficulty);
                if (enteredNameCorrectly) {
                    game.putExtra("name", name);
                }
                game.putExtra("character", character);
                startActivity(game);
                finish();
            }
        });
    }

    /**
     * Function to return the name
     * @param name the name
     * @return returns the name
     */
    public static Boolean getName(String name) {
        if (name == null) {
            return false;
        } else {
            return !name.trim().isEmpty();
        }
    }
}






