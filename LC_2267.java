package leetcode;

import java.util.Arrays;

class LC_2267 {
    int[][][] dp;
    int cnt = 0;
    public boolean hasValidPath(char[][] grid) {
        dp = new int[101][101][201];
        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[i].length;j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        int ans = solve(0,0,0,grid);
        if(ans == 1) {
            return true;
        } else {
            return false;
        }
    }

    int solve(int i, int j, int openCnt, char[][] grid) {
        if(i >= grid.length || j >= grid[0].length) return 0;

        if(dp[i][j][openCnt] != -1) return dp[i][j][openCnt];
        cnt++;
        int originOpenCnt = openCnt;
        if(grid[i][j] == '(') openCnt++;
        else openCnt--;

        int ret;
        if(openCnt < 0) return 0;
        else if(i == grid.length-1 && j == grid[0].length-1) {
            if(openCnt == 0) ret = 1;
            else ret = 0;
        } else {
            ret = solve(i+1, j, openCnt, grid);
            if(ret != 1) {
                ret = solve(i,j+1, openCnt, grid);
            }
        }
        dp[i][j][originOpenCnt] = ret;
        return ret;
    }
}