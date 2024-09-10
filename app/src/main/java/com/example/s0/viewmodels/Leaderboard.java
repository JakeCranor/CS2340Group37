package com.example.s0.viewmodels;

import java.util.ArrayList;
import java.util.Comparator;

public class Leaderboard {
    private static Leaderboard rankInstance; // static variable representing the instant rank
    private ArrayList<PlayerRecord> board = new ArrayList<>(); // arraylist representing leaderboard

    /**
     * comparator used to compare scores of two players to
     * determine their respective positions on the leaderboard
     */
    private static Comparator<PlayerRecord> scoreComparator = new Comparator<PlayerRecord>() {
        @Override
        public int compare(PlayerRecord p1, PlayerRecord p2) {
            int score1 = p1.getScore();
            int score2 = p2.getScore();
            return score2 - score1;
        }
    };

    public static Leaderboard getInstance() {
        if (rankInstance == null) {
            rankInstance = new Leaderboard();
        }
        return rankInstance;
    }
    
    public void addRank(PlayerRecord record) {
        board.add(record);
    }

    public String getRankText(int rank) {
        updateRank();
        if (board.size() > rank) {
            return (rank + 1) + ": " + board.get(rank).getName() + " - "
                    + board.get(rank).getScore() + " - " + board.get(rank).getTime();
        }
        return (rank + 1) + ": Empty";
    }

    private void updateRank() {
        board.sort(scoreComparator);
    }


}