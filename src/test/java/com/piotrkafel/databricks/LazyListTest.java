package com.piotrkafel.databricks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LazyListTest {

    @Test
    public void test() {
        Assertions.assertEquals(2, LazyListImpl.of(List.of(1,2,3,4,5))
                .map(i -> i * i)
                .map(i -> i + ":00")
                .indexOf("9:00"));
    }
}
