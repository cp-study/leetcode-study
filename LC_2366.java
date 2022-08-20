package leetcode;

class LC_2366 {
    public long minimumReplacement(int[] nums) {
        int last = 1000000001;

        long ans = 0;
        for(int i=nums.length-1;i>=0;i--) {
            if(nums[i] <= last) {
                last = nums[i];
                continue;
            } else {
                int cnt = (int)Math.ceil((double)nums[i]/last);
                last = nums[i]/cnt;
                ans += cnt-1;
            }
        }
        return ans;
    }
}