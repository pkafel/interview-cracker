package com.piotrkafel.atlassian.rate.limiter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

// T can be any class as long as we can use it for HashMap lookup
// (proper implementation of equals and hashCode is required).
public class FixedWindowRateLimiterSimple<T> {

    private final int windowMaxCapacity;

    private final long windowSizeInNanos;

    private final IClock clock;

    private final Map<T, Window> store = new HashMap<>();

    public FixedWindowRateLimiterSimple(int windowMaxCapacity, long windowLength, TimeUnit windowLengthTimeUnit, IClock clock) {
        if(windowMaxCapacity < 1) throw new IllegalArgumentException("Window size cannot be smaller than 1");
        this.windowMaxCapacity = windowMaxCapacity;
        this.windowSizeInNanos = windowLengthTimeUnit.toNanos(windowLength);
        this.clock = clock;
    }

    public synchronized boolean handleRequest(T key) {
        // Its important to use monotonic clock here. System.currentTimeMillis is not recommended as with it  we can
        // observe time going backward.
        final long currentTimeNanos = clock.nanoTime();

        Window window = store.computeIfAbsent(key, k -> new Window(currentTimeNanos));

        // Check if we are in another window. If so lets reset the window.
        if(currentTimeNanos - window.getBeginOfWindowInNanos() > windowSizeInNanos) {
            // This implementation assumes we do not need to have windows exactly next to each other.
            // The only thing it will ensure is that the windows will be of the fixed size.
            // If it is an requirement that windows should be adjacent then we can iterate
            // over start of each window by adding windowSizeInNanos until we find first thats
            // bigger than currentTimeNanos. Subtract from it windowSizeInNanos will give you start of
            // current window.
            window.resetWindowWithNewBegin(currentTimeNanos, 1);
            return true;
        }

        // Check if number of requests + 1 is below max allowed window capacity.
        if(window.getNumberOfRequests() + 1 <= windowMaxCapacity) {
            window.increaseNumberOfRequests();
            return true;
        }

        return false;
    }
}

