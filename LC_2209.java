package leetcode;

class LC_2209 {
    public int minimumWhiteTiles(String floor, int numCarpets, int carpetLen) {
        int dp[][] = new int[floor.length()][numCarpets+1];
        for(int i=0;i<dp.length;i++) {
            for(int j=0;j<dp[0].length;j++) {
                dp[i][j] = -1;
            }
        }
        return solve(0, numCarpets, floor, carpetLen, dp);
    }

    private int solve(int idx, int carpetsCnt, String floor, int carpetLen, int dp[][]) {
        if(idx >= floor.length()) return 0;
        if(carpetsCnt == 0) {
            int ret = 0;
            for(int i=idx;i<floor.length();i++){
                if(floor.charAt(i) == '1') ret++;
            }
            return ret;
        }
        if(dp[idx][carpetsCnt] != -1) return dp[idx][carpetsCnt];

        int ret = solve(idx+1, carpetsCnt, floor, carpetLen, dp);
        if(floor.charAt(idx) == '1') ret++;

        ret = Math.min(ret, solve(idx+carpetLen, carpetsCnt-1, floor, carpetLen, dp));
        dp[idx][carpetsCnt] = ret;
        return ret;
    }
}
