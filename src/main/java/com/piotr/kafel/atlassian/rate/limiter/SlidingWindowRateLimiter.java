package com.piotr.kafel.atlassian.rate.limiter;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowRateLimiter<T> {

    private final int windowMaxCapacity;

    private final long windowSizeInNanos;

    private final IClock clock;

    private final Map<T, Deque<Long>> store = new ConcurrentHashMap<>();

    public SlidingWindowRateLimiter(int windowMaxCapacity, long windowSizeInNanos, IClock clock) {
        this.windowMaxCapacity = windowMaxCapacity;
        this.windowSizeInNanos = windowSizeInNanos;
        this.clock = clock;
    }

    public boolean handleRequest(T key) {
        // Its important to use monotonic clock here. System.currentTimeMillis is not recommended as with it  we can
        // observe time going backward.
        final long currentTimeNanos = clock.nanoTime();
        // Alternative to using ConcurrentHashMap.computeIfAbsent would be to have a synchronized method that
        // for a given key returns window.
        final Deque<Long> window = store.computeIfAbsent(key, k -> new LinkedList<>());

        synchronized (window) {
            
            while (!window.isEmpty() && window.peek() < currentTimeNanos - windowSizeInNanos) {
                window.poll();
            }

            if (window.size() < windowMaxCapacity) {
                window.offer(currentTimeNanos);
                return true;
            }
        }

        return false;
    }
}
