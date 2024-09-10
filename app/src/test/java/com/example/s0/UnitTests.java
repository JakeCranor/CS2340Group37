package com.example.s0;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.s0.model.GameActivity;
import com.example.s0.viewmodels.ConfigScreen;
import com.example.s0.views.PlayerView;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTests {

    @Test
    public void nameIsFalseWhenEmpty() { assertFalse(ConfigScreen.getName("   "));}

    @Test
    public void nameIsFalseWhenNull() { assertFalse(ConfigScreen.getName(null));}

    @Test
    public void nameIsTrueWhenReal() { assertTrue(ConfigScreen.getName("MyName"));}

    @Test
    public void testEasyHealth() {
        // Test for the "Easy" radio button
        assertEquals(GameActivity.health(0.5), 600);
    }

    @Test
    public void testMediumHealth() {
        // Test for the "Easy" radio button
        assertEquals(GameActivity.health(0.75), 400);
    }

    @Test
    public void testHardHealth() {
        // Test for the "Easy" radio button
        assertEquals(GameActivity.health(1), 300);
    }

    @Test
    public void testPositionY() {
        PlayerView player = new PlayerView(null, 15, 20, 1);
        assertEquals((int)player.getY(), 20);
    }

    @Test
    public void testPositionX() {
        PlayerView player = new PlayerView(null, 15, 20, 1);
        assertEquals((int)player.getX(), 15);
    }
    @Test
    public void testPositionChangeY() {
        PlayerView player = new PlayerView(null, 15, 20, 1);
        player.updatePosition(10,10);
        assertEquals((int)player.getY(), 10);
    }

    @Test
    public void testPositionChangeX() {
        PlayerView player = new PlayerView(null, 15, 20, 1);
        player.updatePosition(10,10);
        assertEquals((int)player.getX(), 10);
    }








}