package com.example.wonuplt;

public class Thumbnail {
    private int color;
    private float alphaVal;

    public Thumbnail(int color, float alphaVal) {
        this.color = color;
        this.alphaVal = alphaVal;
    }

    public int getColor() {
        return color;
    }

    public float getAlphaVal() {
        return alphaVal;
    }
}
