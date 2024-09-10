package com.example.s0;

import android.view.KeyEvent;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import com.example.s0.viewmodels.RoomOne;

public class Sprint5Test {

    private RoomOne roomOne;

    @Before
    public void setUp() {
        roomOne = new RoomOne();
    }

    @Test
    public void testPowerUpCollision() {
        float initialX = roomOne.getPlayerX();
        float initialY = roomOne.getPlayerY();
        roomOne.onKeyDown(KeyEvent.KEYCODE_DPAD_UP, null);
        roomOne.onKeyDown(KeyEvent.KEYCODE_DPAD_LEFT, null);
        float newX = roomOne.getPlayerX();
        float newY = roomOne.getPlayerY();
        assertEquals(initialX - 20, newX, 0.01);
        assertEquals(initialY - 20, newY, 0.01);
    }

    @Test
    public void testDrawPowerUp() {
        int children = roomOne.getGameLayout().getChildCount();
        assertEquals(1, children);
    }

    @Test
    public void testShooterXSpace() {
        float initialX = roomOne.getShooterX();
        // Press space 3 times
        for (int i = 0; i < 3; i++) {
            roomOne.onKeyDown(KeyEvent.KEYCODE_SPACE, null);
        }
        float newX = roomOne.getShooterX();
        assertEquals(initialX, newX, 100);
    }

    @Test
    public void testShooterYSpace() {
        float initialY = roomOne.getShooterY();
        // Press space 3 times
        for (int i = 0; i < 3; i++) {
            roomOne.onKeyDown(KeyEvent.KEYCODE_SPACE, null);
        }
        float newY = roomOne.getShooterY();
        assertEquals(initialY, newY, 100);
    }

    @Test
    public void testShooterXSpace5() {
        float initialX = 20000;
        // Press space 5 times
        for (int i = 0; i < 5; i++) {
            roomOne.onKeyDown(KeyEvent.KEYCODE_SPACE, null);
        }
        float newX = roomOne.getShooterX();
        assertEquals(initialX, newX, 0);
    }

    @Test
    public void testShooterYSpace5() {
        float initialY = 20000;
        // Press space 5 times
        for (int i = 0; i < 5; i++) {
            roomOne.onKeyDown(KeyEvent.KEYCODE_SPACE, null);
        }
        float newY = roomOne.getShooterY();
        assertEquals(initialY, newY, 0);
    }

    @Test
    public void testHealthZero() {
        // call collisionDetectedEnemy1 when it returns true until health is 0
        int initialHealth = roomOne.getHealth();
        while (roomOne.collisionDetectedEnemy3(roomOne.getPlayerX(), roomOne.getPlayerY(), roomOne.getEnemyOneX(), roomOne.getEnemyOneY()) == false) {
            roomOne.onKeyDown(KeyEvent.KEYCODE_DPAD_DOWN, null);
            roomOne.onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT, null);
        }
        int newHealth = roomOne.getHealth();
        assertEquals(initialHealth - 1, newHealth, 10);
    }

    @Test
    public void testScoreDecrease() {
        int initialScore = roomOne.getScore();
        while (roomOne.collisionDetectedEnemy3(roomOne.getPlayerX(), roomOne.getPlayerY(), roomOne.getEnemyOneX(), roomOne.getEnemyOneY()) == false) {
            roomOne.onKeyDown(KeyEvent.KEYCODE_DPAD_DOWN, null);
            roomOne.onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT, null);
        }
        int newScore = roomOne.getScore();
        assertEquals(initialScore - 1, newScore, 20000);
    }

    @Test
    public void testHealthEnemy4() {
        // call collisionDetectedEnemy1 when it returns true until health is 0
        int initialHealth = roomOne.getHealth();
        while (roomOne.collisionDetectedEnemy4(roomOne.getPlayerX(), roomOne.getPlayerY(), roomOne.getEnemyOneX(), roomOne.getEnemyOneY()) == false) {
            roomOne.onKeyDown(KeyEvent.KEYCODE_DPAD_DOWN, null);
            roomOne.onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT, null);
        }
        int newHealth = roomOne.getHealth();
        assertEquals(initialHealth - 1, newHealth, 10);
    }

    @Test
    public void testScoreEnemy4() {
        int initialScore = roomOne.getScore();
        while (roomOne.collisionDetectedEnemy4(roomOne.getPlayerX(), roomOne.getPlayerY(), roomOne.getEnemyOneX(), roomOne.getEnemyOneY()) == false) {
            roomOne.onKeyDown(KeyEvent.KEYCODE_DPAD_DOWN, null);
            roomOne.onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT, null);
        }
        int newScore = roomOne.getScore();
        assertEquals(initialScore - 1, newScore, 20000);
    }
}