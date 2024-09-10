package com.example.s0.viewmodels;

public class PlayerMovement implements Movement {
    // strategy design pattern

    @Override
    public float moveRight(float x) {
        if (x <= 1100) {
            x += 20;
        }
        return x;
    }

    @Override
    public float moveLeft(float x) {
        if (x >= 340) {
            x -= 20;
        }
        return x;
    }

    @Override
    public float moveUp(float y) {
        if (y >= 350) {
            y -= 20;
        }
        return y;
    }

    @Override
    public float moveDown(float y) {
        if (y <= 2260) {
            y += 20;
        }
        return y;
    }
    
}
