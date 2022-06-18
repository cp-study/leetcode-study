package leetcode;

class LC_2302 {
    public long countSubarrays(int[] nums, long k) {
        long ret = 0;

        long accum = 0;
        long[] prefixSum = new long[nums.length];
        for(int i=0;i<nums.length;i++) {
            if(i>0) {
                prefixSum[i] += prefixSum[i-1];
            }
            prefixSum[i] += nums[i];
        }

        for(int i=0;i<prefixSum.length;i++) {

            int l = i;
            int r = prefixSum.length-1;
            int m = 0;
            while(l<=r) {
                m = (l+r)/2;

                long val = (prefixSum[m]-accum)*(m-i+1);

                if(val >= k) {
                    r = m-1;
                } else if(val < k) {
                    l = m+1;
                }
            }
            ret += r-i+1;

            accum += nums[i];
        }

        return ret;
    }
}