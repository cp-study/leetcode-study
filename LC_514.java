package leetcode;

class LC_514 {
    public int findRotateSteps(String ring, String key) {
        int dp[][] = new int[key.length()][ring.length()];
        for(int i=0;i<dp.length;i++) {
            for(int j=0;j<dp[0].length;j++) {
                dp[i][j] = -1;
            }
        }

        return solve(0,0,ring,key, dp);
    }

    private int solve(int keyIdx, int idx, String ring, String key, int dp[][]) {
        if(keyIdx >= key.length()) return 0;

        if(dp[keyIdx][idx] != -1) return dp[keyIdx][idx];

        int idxLeft = idx;
        int distLeft = 0;
        int idxRight = idx;
        int distRight = 0;

        while(ring.charAt(idxLeft) != key.charAt(keyIdx)) {
            idxLeft--;
            distLeft++;
            idxLeft = (idxLeft+ring.length()) % ring.length();
        }
        while(ring.charAt(idxRight) != key.charAt(keyIdx)) {
            idxRight++;
            distRight++;
            idxRight %= ring.length();
        }

        int ret = 1+Math.min(distLeft + solve(keyIdx+1, idxLeft, ring,key, dp),
                distRight + solve(keyIdx+1, idxRight, ring,key, dp));
        dp[keyIdx][idx] = ret;
        return ret;
    }
}