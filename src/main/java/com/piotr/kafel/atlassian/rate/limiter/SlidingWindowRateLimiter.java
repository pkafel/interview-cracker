package com.piotr.kafel.atlassian.rate.limiter;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class SlidingWindowRateLimiter<T> {

    private final int windowMaxCapacity;

    private final long windowSizeInNanos;

    private final IClock clock;

    private final Map<T, Deque<Long>> store = new ConcurrentHashMap<>();

    public SlidingWindowRateLimiter(int windowMaxCapacity, long windowLength, TimeUnit windowLengthTimeUnit, IClock clock) {
        if(windowMaxCapacity < 1) throw new IllegalArgumentException("Window size cannot be smaller than 1");
        this.windowMaxCapacity = windowMaxCapacity;
        this.windowSizeInNanos = windowLengthTimeUnit.toNanos(windowLength);
        this.clock = clock;
    }

    public boolean handleRequest(T key) {
        // Its important to use monotonic clock here. System.currentTimeMillis is not recommended as with it  we can
        // observe time going backward.
        final long currentTimeNanos = clock.nanoTime();
        // Alternative to using ConcurrentHashMap.computeIfAbsent would be to have a synchronized method that
        // for a given key returns window.
        final Deque<Long> window = store.computeIfAbsent(key, k -> new LinkedList<>());

        // To be sure we do not mess up the window we need to synchronize it.
        // There is a synchronized Deque implementation but it will not allow us to make .size() and .offer in
        // single operation. That means we actually make end up in situation where window would become
        // bigger than windowMaxCapacity.
        synchronized (window) {
            // Remove the oldest entries (from head side) that are outside of current sliding window.
            while (!window.isEmpty() && window.peek() <= currentTimeNanos - windowSizeInNanos) {
                window.poll();
            }
            // Check current size of sliding window and if smaller than windowMaxCapacity add new entry (to tail).
            if (window.size() < windowMaxCapacity) {
                window.offer(currentTimeNanos);
                return true;
            }
        }

        return false;
    }
}
