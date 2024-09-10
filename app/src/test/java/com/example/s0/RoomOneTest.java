package com.example.s0;

import android.os.Looper;
import android.view.KeyEvent;
import org.junit.Before;
import org.junit.Test;
//import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

import com.example.s0.viewmodels.RoomOne;

public class RoomOneTest {

    private RoomOne roomOne;


    @Before
    public void setUp() {
        roomOne = new RoomOne();
    }

    @Test
    public void testMoveLeft() {
        float initialX = roomOne.getPlayerX();
        roomOne.onKeyDown(KeyEvent.KEYCODE_DPAD_LEFT, null);
        float newX = roomOne.getPlayerX();
        assertEquals(initialX - 20, newX, 0.01);
    }

    @Test
    public void testMoveRight() {
        float initialX = roomOne.getPlayerX();
        roomOne.onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT, null);
        float newX = roomOne.getPlayerX();
        assertEquals(initialX + 20, newX, 0.01);
    }

    @Test
    public void testMoveUp() {
        float initialY = roomOne.getPlayerY();
        roomOne.onKeyDown(KeyEvent.KEYCODE_DPAD_UP, null);
        float newY = roomOne.getPlayerY();
        assertEquals(initialY - 20, newY, 0.01);
    }

    @Test
    public void testMoveDown() {
        float initialY = roomOne.getPlayerY();
        roomOne.onKeyDown(KeyEvent.KEYCODE_DPAD_DOWN, null);
        float newY = roomOne.getPlayerY();
        assertEquals(initialY + 20, newY, 0.01);
    }

    @Test
    public void testMoveUpLeft() {
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
    public void testPlayerMovesLeftOutOfBounds() {
        roomOne.setPlayerX(300); // Set playerX to a position out of bounds
        roomOne.onKeyDown(KeyEvent.KEYCODE_DPAD_LEFT, null);

        assertEquals(300, roomOne.getPlayerX(), 0.01);
    }

    @Test
    public void testPlayerMovesUpOutOfBounds() {
        roomOne.setPlayerY(550); // Set playerY to a position out of bounds
        roomOne.onKeyDown(KeyEvent.KEYCODE_DPAD_UP, null);

        assertEquals(550, roomOne.getPlayerY(), 0.01);
    }

    @Test
    public void testPlayerMovesRightOutOfBounds() {
        roomOne.setPlayerX(2500); // Set playerX to a position out of bounds
        roomOne.onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT, null);

        assertEquals(2500, roomOne.getPlayerX(), 0.01);
    }

    @Test
    public void testPlayerMovesDownOutOfBounds() {
        roomOne.setPlayerY(2500); // Set playerY to a position out of bounds
        roomOne.onKeyDown(KeyEvent.KEYCODE_DPAD_DOWN, null);

        assertEquals(2500, roomOne.getPlayerY(), 0.01);
    }

    @Test
    public void testPlayerMovesUpLeftOutOfBounds() {
        roomOne.setPlayerX(300); // Set playerX to a position out of bounds
        roomOne.setPlayerY(550); // Set playerY to a position out of bounds
        roomOne.onKeyDown(KeyEvent.KEYCODE_DPAD_UP, null);
        roomOne.onKeyDown(KeyEvent.KEYCODE_DPAD_LEFT, null);

        assertEquals(300, roomOne.getPlayerX(), 0.01);
        assertEquals(550, roomOne.getPlayerY(), 0.01);
    }

}
