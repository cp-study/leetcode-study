package leetcode;

import java.util.*;

class LC_1187 {
    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        List<HashMap<Integer,Integer>> dp = new ArrayList<>();
        for(int i=0;i<arr1.length;i++) {
            dp.add(new HashMap<>());
        }
        Arrays.sort(arr2);
        int ans = solveMakeArrayIncreasing(0,-1,arr1,arr2, dp);
        if(ans == Integer.MAX_VALUE/2) {
            ans = -1;
        }
        return ans;
    }

    private int solveMakeArrayIncreasing(int n, int biggerThan, int[] arr1, int[] arr2,
                                         List<HashMap<Integer,Integer>> dp) {
        if(n==arr1.length) return 0;
        if(dp.get(n).containsKey(biggerThan)) {
            return dp.get(n).get(biggerThan);
        }
        int ret = Integer.MAX_VALUE/2;
        if(arr1[n] > biggerThan) {
            ret = solveMakeArrayIncreasing(n + 1, arr1[n], arr1, arr2, dp);
        }
        int l =0;
        int r = arr2.length-1;
        int m = 0;
        while(l<=r) {
            m = (l+r)/2;
            if(arr2[m] > biggerThan) {
                r = m-1;
            } else if(arr2[m] <= biggerThan) {
                l = m+1;
            }
        }
        if(l<arr2.length) ret = Math.min(ret, solveMakeArrayIncreasing(n+1,arr2[l], arr1,arr2, dp)+1);

        dp.get(n).put(biggerThan,ret);
        return ret;
    }
}