package leetcode;

import java.util.Arrays;
import java.util.List;

class LC_2826 {
    public int minimumOperations(List<Integer> nums) {
        int dp[][] = new int[nums.size()][4];
        for(int i=0;i<nums.size();i++) {
            Arrays.fill(dp[i], -1);
        }
        return solve(0, 0, nums, dp);
    }

    int solve(int idx, int currSmallest, List<Integer> nums, int dp[][]) {
        if(idx >= nums.size()) return 0;

        if(dp[idx][currSmallest] != -1) return dp[idx][currSmallest];

        int ret = 0;
        if(currSmallest == nums.get(idx)) {
            ret = solve(idx+1, currSmallest, nums, dp);
        } else if(currSmallest > nums.get(idx)){
            ret = solve(idx+1, currSmallest, nums, dp) + 1;
        } else {
            ret = Math.min(solve(idx+1, currSmallest, nums, dp)+1,
                    solve(idx+1, nums.get(idx), nums, dp));
        }
        return dp[idx][currSmallest] = ret;
    }
}