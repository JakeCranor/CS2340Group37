package com.example.s0.viewmodels;

public class PlayerRecord {
    private int score;
    private String name;
    private String time;

    /**
     * constructor for the player record
     * @param score score of player
     * @param name name of player
     * @param time time of player
     */
    public PlayerRecord(int score, String name, String time) {
        this.score = score;
        this.name = name;
        this.time = time;
    }
    /**
     * void method to update the score of player
     * @param score score of player
     * @param name name of player
     * @param time time of player
     */
    public void updateRecord(int score, String name, String time) {
        this.score = score;
        this.name = name;
        this.time = time;
    }


    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }
}
