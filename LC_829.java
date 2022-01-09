package main;

public class LC_829 {
    public int consecutiveNumbersSum(int n) {
        int ans = 1;
        for (int i = 1; i <= Math.pow(n, 0.5) + 1; i++) {
            if ((2 * n) % (i + 1) != 0) continue;
            int m = (int) Math.floor(n / (i + 1.0) - i / 2.0);
            if (m>0 && (i + 1) * (2*m + i) / 2 == n)
                ans += 1;
        }
        return ans;
    }
}
