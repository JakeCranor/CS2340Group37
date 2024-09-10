package com.example.s0;

import static org.junit.Assert.assertEquals;

import android.view.KeyEvent;

import com.example.s0.viewmodels.RoomOne;
import com.example.s0.viewmodels.RoomThree;
import com.example.s0.viewmodels.RoomTwo;

import org.junit.Before;
import org.junit.Test;

public class EnemyTest {
    private RoomOne roomOne;
    private RoomTwo roomTwo;
    private RoomThree roomThree;


    @Before
    public void setUp() {
        roomOne = new RoomOne();
        roomTwo = new RoomTwo();
        roomThree = new RoomThree();
    }

    @Test
    public void testHealthDecreaseEnemyOne() {
        int initialHealth = roomOne.getHealth();
        while (roomOne.collisionDetectedEnemy3(roomOne.getPlayerX(), roomOne.getPlayerY(), roomOne.getEnemyOneX(), roomOne.getEnemyOneY()) == false) {
            roomOne.onKeyDown(KeyEvent.KEYCODE_DPAD_UP, null);
        }
        int newHealth = roomOne.getHealth();
        assertEquals(initialHealth - 1, newHealth);
    }

    @Test
    public void testHealthDecreaseEnemyTwo() {
        int initialHealth = roomOne.getHealth();
        while (roomOne.collisionDetectedEnemy4(roomOne.getPlayerX(), roomOne.getPlayerY(), roomOne.getEnemyTwoX(), roomOne.getEnemyTwoY()) == false) {
            roomOne.onKeyDown(KeyEvent.KEYCODE_DPAD_UP, null);
        }
        int newHealth = roomOne.getHealth();
        assertEquals(initialHealth - 1, newHealth);
    }

    // Room Two Tests
    @Test
    public void testHealthDecreaseEnemyThree() {
        int initialHealth = roomTwo.getHealth();
        while (roomTwo.collisionDetectedEnemy1(roomTwo.getPlayerX(), roomTwo.getPlayerY(), roomTwo.getEnemyOneX(), roomTwo.getEnemyOneY()) == false) {
            roomTwo.onKeyDown(KeyEvent.KEYCODE_DPAD_UP, null);
        }
        int newHealth = roomTwo.getHealth();
        assertEquals(initialHealth - 1, newHealth);
    }

    @Test
    public void testHealthDecreaseEnemyFour() {
        int initialHealth = roomTwo.getHealth();
        while (roomTwo.collisionDetectedEnemy2(roomTwo.getPlayerX(), roomTwo.getPlayerY(), roomTwo.getEnemyTwoX(), roomTwo.getEnemyTwoY()) == false) {
            roomTwo.onKeyDown(KeyEvent.KEYCODE_DPAD_UP, null);
        }
        int newHealth = roomTwo.getHealth();
        assertEquals(initialHealth - 1, newHealth);
    }

    // Room Three Tests
    @Test
    public void testHealthDecreaseEnemyFive() {
        int initialHealth = roomThree.getHealth();
        while (roomThree.collisionDetectedEnemy1(roomThree.getPlayerX(), roomThree.getPlayerY(), roomThree.getEnemyOneX(), roomThree.getEnemyOneY()) == false) {
            roomThree.onKeyDown(KeyEvent.KEYCODE_DPAD_UP, null);
        }
        int newHealth = roomThree.getHealth();
        assertEquals(initialHealth - 1, newHealth);
    }

    @Test
    public void testHealthDecreaseEnemySix() {
        int initialHealth = roomThree.getHealth();
        while (roomThree.collisionDetectedEnemy2(roomThree.getPlayerX(), roomThree.getPlayerY(), roomThree.getEnemyTwoX(), roomThree.getEnemyTwoY()) == false) {
            roomThree.onKeyDown(KeyEvent.KEYCODE_DPAD_UP, null);
        }
        int newHealth = roomThree.getHealth();
        assertEquals(initialHealth - 1, newHealth);
    }

    @Test
    public void testEnemyOneMoveLeft() {
        float initialX = roomOne.getEnemyOneX();
        roomOne.onKeyDown(KeyEvent.KEYCODE_DPAD_LEFT, null);
        float newX = roomOne.getEnemyOneX();
        assertEquals(initialX - 20, newX, 0.01);
    }

    @Test
    public void testEnemyOneMoveRight() {
        float initialX = roomOne.getEnemyOneX();
        roomOne.onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT, null);
        float newX = roomOne.getEnemyOneX();
        assertEquals(initialX + 20, newX, 0.01);
    }

    @Test
    public void testEnemyOneMoveUp() {
        float initialY = roomOne.getEnemyOneY();
        roomOne.onKeyDown(KeyEvent.KEYCODE_DPAD_UP, null);
        float newY = roomOne.getEnemyOneY();
        assertEquals(initialY - 20, newY, 0.01);
    }


    @Test
    public void testEnemyOneMoveDown() {
        float initialY = roomOne.getEnemyOneY();
        roomOne.onKeyDown(KeyEvent.KEYCODE_DPAD_DOWN, null);
        float newY = roomOne.getEnemyOneY();
        assertEquals(initialY + 20, newY, 0.01);
    }

}
