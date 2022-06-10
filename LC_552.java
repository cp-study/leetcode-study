package leetcode;

import java.util.Arrays;

class LC_552 {
    private static final int MOD = 1000000007;

    public int checkRecord(int n) {
        int[][][] dp = new int[2][4][n+1];


        for(int i=0;i<2;i++) {
            for(int j=0;j<4;j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        return solve(0,0, n, dp);
    }

    /**
     * flag 0: 'P'
     * flag 1: 'A'
     * flag 2: 'L'
     * flag 3: 'LL'
     */
    int solve(int A, int flag, int n, int[][][] dp) {
        if(n==0) return 1;

        if(dp[A][flag][n] != -1) return dp[A][flag][n];

        int ret = 0;
        if(flag >=0 && flag <=3) {
            ret += solve(A,0, n-1, dp);
        }
        ret %=MOD;
        if(A==0) {
            ret += solve(A+1, 1, n-1, dp);
        }
        ret %=MOD;
        if(flag >= 0 && flag <=2) {
            if(flag == 2) {
                ret += solve(A,3, n-1, dp);
            } else {
                ret += solve(A,2, n-1, dp);
            }
        }
        ret %=MOD;

        dp[A][flag][n] = ret;
        return ret;
    }
}