package com.piotr.kafel.atlassian.rate.limiter;

import com.piotr.kafel.atlassian.rate.limiter.util.MockClock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class SlidingWindowRateLimiterTest {

    private MockClock clock;

    @BeforeEach
    public void setup() {
        clock = new MockClock();
    }

    @Test
    public void shouldThrowExceptionWhenPassedNegativeNumberOfMaxRequests() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new SlidingWindowRateLimiter<Long>(-1, 2, TimeUnit.NANOSECONDS, clock));
    }

    @Test
    public void shouldAllowOnlyRequestBelowCapacityPerWindow() {
        final UUID requestId = UUID.randomUUID();
        final SlidingWindowRateLimiter<UUID> rateLimiter = new SlidingWindowRateLimiter<>(2, 3, TimeUnit.NANOSECONDS, clock);

        Assertions.assertTrue(rateLimiter.handleRequest(requestId));
        clock.increaseBy(1);
        Assertions.assertTrue(rateLimiter.handleRequest(requestId));
        Assertions.assertFalse(rateLimiter.handleRequest(requestId));
        clock.increaseBy(2);
        Assertions.assertTrue(rateLimiter.handleRequest(requestId));
    }
}
