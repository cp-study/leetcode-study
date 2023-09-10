package leetcode;

class LC_2834 {
    private static final int MOD = 1000000007;
    public int minimumPossibleSum(int n, int target) {
        long half = Math.min(n, target / 2);

        long currLeft = n - half;

        long ans = (half * (half + 1)) / 2 % MOD;

        long start = target;

        ans += ((start + (start + currLeft - 1)) * currLeft) / 2;
        ans %= MOD;

        return (int) ans;
    }
}