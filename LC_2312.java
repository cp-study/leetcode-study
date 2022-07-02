package leetcode;

import java.util.Arrays;
import java.util.HashMap;

class LC_2312 {
    public long sellingWood(int m, int n, int[][] prices) {

        HashMap<Pair,Integer> pricesMap = new HashMap<>();
        for(int i=0;i<prices.length;i++) {
            pricesMap.put(new Pair(prices[i][0], prices[i][1]), prices[i][2]);
        }

        long[][] dp = new long[m+1][n+1];

        for(int i=0;i<dp.length;i++) {
            Arrays.fill(dp[i],-1);
        }

        return solve(m,n, pricesMap, dp);
    }

    long solve(int h, int w, HashMap<Pair,Integer> pricesMap, long[][] dp) {
        if(dp[h][w] != -1) return dp[h][w];

        long ret = 0;
        if(pricesMap.containsKey(new Pair(h,w))) {
            ret = pricesMap.get(new Pair(h,w));
        }

        for(int i=1;i<h/2+1;i++) {
            ret = Math.max(ret, solve(i, w, pricesMap,dp) + solve(h - i, w, pricesMap,dp));
        }

        for(int i=1;i<w/2+1;i++) {
            ret = Math.max(ret, solve(h, i, pricesMap,dp) + solve(h, w-i, pricesMap,dp));
        }
        dp[h][w] = ret;
        return ret;
    }

    class Pair {
        int h,w;
        public Pair(int h, int w) {
            this.h = h;
            this.w = w;
        }

        @Override
        public boolean equals(Object p) {
            Pair pCast = (Pair) p;
            if(this.h == pCast.h && this.w == pCast.w) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return h*1000+w;
        }
    }
}