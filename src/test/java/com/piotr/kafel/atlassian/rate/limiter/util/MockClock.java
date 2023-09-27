package com.piotr.kafel.atlassian.rate.limiter.util;

import com.piotr.kafel.atlassian.rate.limiter.IClock;

public class MockClock implements IClock {

    private long currentTime = 1;

    public void increaseBy(long value) {
        currentTime += value;
    }

    @Override
    public long nanoTime() {
        return currentTime;
    }
}
