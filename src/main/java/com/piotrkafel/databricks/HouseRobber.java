package com.piotrkafel.databricks;

import java.util.Arrays;

public class HouseRobber {

    // House Robber part I
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

    // House Robber part II

    public int rob2(int[] nums) {
        int[] cacheUsedLast = new int[nums.length];
        int[] cacheDidntUseLast = new int[nums.length];
        Arrays.fill(cacheUsedLast, -1);
        Arrays.fill(cacheDidntUseLast, -1);

        int index=nums.length - 1;
        return Math.max(
                nums[index] + rob(nums, cacheUsedLast, index - 2, true),
                rob(nums, cacheDidntUseLast, index - 1, false)
        );
    }

    private int rob(int[] nums, int[] resultsCache, int index, boolean usedLast) {
        if(index < 0 || (index == 0 && usedLast)) return 0;
        if(resultsCache[index]!=-1) return resultsCache[index];

        int result = Math.max(
                nums[index] + rob(nums, resultsCache, index - 2, usedLast),
                rob(nums, resultsCache, index - 1, usedLast)
        );
        resultsCache[index] = result;
        return result;
    }
}
