package com.piotrkafel.jpmorgan;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VersionComparatorTest {

    @Test
    public void test() {
        Assertions.assertEquals("1.9.12", VersionComparator.Companion.getLatest("1.9.12", "1.8.11"));
        Assertions.assertEquals("1.7.10", VersionComparator.Companion.getLatest("1.7.10", "1.7"));
        Assertions.assertEquals("same", VersionComparator.Companion.getLatest("1.34.0", "1.34"));
        Assertions.assertEquals("1.7.10-bravo", VersionComparator.Companion.getLatest("1.7.10-alpha", "1.7.10-bravo"));
    }
}
