package com.piotrkafel.atlassian.rate.limiter;

public class Window {

    private int numberOfRequests;

    private long beginOfWindowInNanos;

    public Window(long beginOfWindowInNanos) {
        resetWindowWithNewBegin(beginOfWindowInNanos, 0);
    }

    public int getNumberOfRequests() {
        return numberOfRequests;
    }

    public long getBeginOfWindowInNanos() {
        return beginOfWindowInNanos;
    }

    public void resetWindowWithNewBegin(long beginOfWindowInNanos, int numberOfRequests) {
        this.beginOfWindowInNanos = beginOfWindowInNanos;
        this.numberOfRequests = numberOfRequests;
    }

    public void increaseNumberOfRequests() {
        this.numberOfRequests++;
    }
}
