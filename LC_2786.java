package leetcode;

import java.util.Arrays;

class LC_2786 {
    public long maxScore(int[] nums, int x) {
        int n = nums.length;
        long[][] dp = new long[n][2];
        for(long[] arr : dp){
            Arrays.fill(arr,-1);
        }
        return nums[0]+solve(0,nums,x,nums[0]%2,dp);
    }
    long solve(int i,int[] nums, int x, int prev, long[][] dp){
        if(i+1 == nums.length){
            return 0;
        }
        if(dp[i][prev]!=-1){ return dp[i][prev]; }
        long ret = 0;
        if(nums[i+1]%2 == prev){
            ret = nums[i+1]+solve(i+1,nums,x,prev,dp);
        }
        else{
            ret = solve(i+1,nums,x,nums[i+1]%2,dp)-x+nums[i+1];
        }
        ret = Math.max(ret, solve(i+1,nums,x,prev,dp));
        return dp[i][prev] = ret;
    }
}