package com.bowling.service.impl;

public class Bonus {
    private final int frameIndex;
    private int count;
    private int sum = 10;

    public Bonus(int count, int frameIndex) {
        this.count = count;
        this.frameIndex = frameIndex;
    }

    public boolean add(int pins) {
        sum += pins;
        return --count == 0;
    }

    public int getSum() {
        return sum;
    }

    public int getFrameIndex() {
        return frameIndex;
    }
}
