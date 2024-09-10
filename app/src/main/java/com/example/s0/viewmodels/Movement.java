package com.example.s0.viewmodels;

public interface Movement {
    // interface for strategy design pattern
    float moveRight(float x);
    float moveLeft(float x);
    float moveUp(float y);
    float moveDown(float y);
}

