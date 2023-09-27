package com.piotrkafel.atlassian.rate.limiter.util;

import com.piotrkafel.atlassian.rate.limiter.IClock;

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
