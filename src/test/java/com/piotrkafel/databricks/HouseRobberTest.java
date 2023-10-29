package com.piotrkafel.databricks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HouseRobberTest {

    @Test
    public void testHouseRobber2Approach() {
        int[] params = new int[] {200,3,140,20,10};
        Assertions.assertEquals(340, new HouseRobber().rob2(params));
    }
}
