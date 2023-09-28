package com.piotrkafel.stripe;

import org.junit.jupiter.api.Test;

import static com.piotrkafel.stripe.ShopOpeningTimeProblem.computePenalty;
import static com.piotrkafel.stripe.ShopOpeningTimeProblem.findBestClosingTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShopOpeningTimeProblemTest {

    @Test
    public void shouldCalculatePenaltyCorrectly() {
        assertEquals(3, computePenalty("Y Y Y N N N N", 0));
        assertEquals(4, computePenalty("Y Y Y N N N N", 7));
        assertEquals(0, computePenalty("Y Y Y N N N N", 3));
        assertEquals(0, computePenalty("", 0));
        assertEquals(1, computePenalty("Y N Y N N N N", 3));
    }

    @Test
    public void shouldCalculateBestClosingTimeCorrectly() {
        assertEquals(3, findBestClosingTime("Y Y Y N N N N"));
        assertEquals(0, findBestClosingTime(""));
        assertEquals(1, findBestClosingTime("Y"));
        assertEquals(0, findBestClosingTime("N N N N"));
        assertEquals(4, findBestClosingTime("Y Y Y Y"));
        assertEquals(5, findBestClosingTime("N Y Y Y Y N N N Y N N Y Y N N N N Y Y N N Y N N N"));
        assertEquals(0, findBestClosingTime("N N N N N Y Y Y N N N N Y Y Y N N N Y N Y Y N Y N"));
        assertEquals(25, findBestClosingTime("Y Y N N N Y Y N Y Y N N N Y Y N N Y Y Y N Y N Y Y"));
    }
}
