package leetcode;

import java.util.Arrays;

class LC_1563 {
    public long countExcellentPairs(int[] nums, int k) {
        long[] numsDistinct = Arrays.stream(nums).asLongStream().distinct().toArray();

        Arrays.sort(numsDistinct);
        long ret = 0;
        for(int i=0;i<numsDistinct.length-1;i++) {
            System.out.println("here");
            // do binary search
            int l = i+1;
            int r = numsDistinct.length-1;
            int m = 0;
            while(l<=r) {
                m = (l+r)/2;
                long val = (numsDistinct[i]|numsDistinct[m]) + (numsDistinct[i]&numsDistinct[m]);
                System.out.println("val: " + val);
                System.out.println("numsDistinct[i]: " + numsDistinct[i]);
                System.out.println("numsDistinct[m]: " + numsDistinct[m]);
                if(val >= k) {
                    r = m-1;
                } else if(val < k) {
                    l = m+1;
                }
            }
            if(l<numsDistinct.length) {
                System.out.println("numsDistinct[l]: " + numsDistinct[l]);
            }
            ret += numsDistinct.length-l;
            System.out.println("numsDistinct.length-l: " + (numsDistinct.length-l));
        }
        return ret;
    }
}