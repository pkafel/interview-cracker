package com.piotr.kafel.atlassian.rate.limiter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class FixedWindowRateLimiterSimpleTest {

    private MockClock clock;

    @BeforeEach
    public void setup() {
        clock = new MockClock();
    }

    @Test
    public void shouldThrowExceptionWhenPassedNegativeNumberOfMaxRequests() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new FixedWindowRateLimiterSimple<Long>(-1, 2, TimeUnit.NANOSECONDS, clock));
    }

    @Test
    public void shouldAllowOnlyRequestBelowCapacityPerWindow() {
        final UUID requestId = UUID.randomUUID();
        final FixedWindowRateLimiterSimple<UUID> rateLimiter = new FixedWindowRateLimiterSimple<>(1, 5, TimeUnit.NANOSECONDS, clock);

        Assertions.assertTrue(rateLimiter.handleRequest(requestId));
        Assertions.assertFalse(rateLimiter.handleRequest(requestId));

        clock.increaseBy(6);

        Assertions.assertTrue(rateLimiter.handleRequest(requestId));
    }

    public static class MockClock implements IClock {

        private long currentTime = 1;

        public void increaseBy(long value) {
            currentTime += value;
        }

        @Override
        public long nanoTime() {
            return currentTime;
        }
    }
}

