package leetcode;

import java.util.HashMap;

class LC_854 {
    public int kSimilarity(String s1, String s2) {
        HashMap<String,Integer> dp = new HashMap<>();
        StringBuilder sb1 = new StringBuilder(s1);
        return solve(0,sb1, s2, dp);
    }

    public int solve(int idx, StringBuilder s1, String s2, HashMap<String,Integer> dp) {
        if(idx >= s1.length()) return 0;
        if(dp.containsKey(s1.toString())) return dp.get(s1.toString());
        int ret = Integer.MAX_VALUE;
        if(s1.charAt(idx) == s2.charAt(idx)) {
            ret = solve(idx+1, s1,s2, dp);
        } else {
            for(int i=idx+1;i<s2.length();i++) {
                if(s2.charAt(idx) == s1.charAt(i) && s2.charAt(i) != s1.charAt(i)) {
                    swap(idx, i, s1);
                    int curr = 1+solve(idx+1, s1,s2, dp);
                    swap(idx, i, s1);
                    ret = Math.min(ret, curr);
                }
            }
        }
        if(ret == Integer.MAX_VALUE) ret = 0;
        dp.put(s1.toString(), ret);
        return ret;
    }

    private void swap(int i, int j, StringBuilder sb) {
        char tmp = sb.charAt(i);
        sb.replace(i, i+1, sb.charAt(j)+"");
        sb.replace(j, j+1, tmp+"");
    }

}