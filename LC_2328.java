package leetcode;

import java.util.Arrays;

class LC_2328 {
    private static final int MOD = 1000000007;
    int[] dy = {1,-1,0,0};
    int[] dx = {0,0,1,-1};

    public int countPaths(int[][] grid) {

        int[][] dp = new int[grid.length][grid[0].length];

        for(int i=0;i<dp.length;i++) {
            Arrays.fill(dp[i], -1);
        }

        int ret = 0;
        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[0].length;j++) {
                ret += solve(i,j,grid,dp);
                ret %= MOD;
            }
        }

        return ret;
    }

    int solve(int y, int x, int[][] grid,
              int[][] dp) {
        if(dp[y][x] != -1) return dp[y][x];

        long ret = 1;
        for(int i=0;i<4;i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(ny >= grid.length || nx >= grid[0].length
                    || ny<0 || nx<0) continue;
            if(grid[ny][nx] > grid[y][x]) {
                ret += solve(ny, nx, grid, dp);
                ret %= MOD;
            }
        }

        dp[y][x] = (int)ret;
        return (int)ret;
    }
}