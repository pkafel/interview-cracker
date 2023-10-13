package com.piotrkafel.databricks;

import java.util.Arrays;

public class HouseRobber {

    public int rob(int[] nums) {
        int[] cache = new int[nums.length];
        Arrays.fill(cache, -1);
        return rob(nums, cache, nums.length - 1);
    }

    // solution with memoization
    private int rob(int[] nums, int[] resultsCache, int index) {
        if(index < 0) return 0;
        if(resultsCache[index]!=-1) return resultsCache[index];

        int result = Math.max(
                nums[index] + rob(nums, resultsCache, index - 2),
                rob(nums, resultsCache, index - 1)
        );
        resultsCache[index] = result;
        return result;
    }

    // solution without memoization
    private int rob(int[] nums, int index) {
        if(index < 0) return 0;

        return Math.max(nums[index] + rob(nums, index - 2), rob(nums, index - 1));
    }
}
