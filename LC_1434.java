package main;

import java.util.List;

public class LC_1434 {
    public int numberWays(List<List<Integer>> hats) {
        long Mod = 1000000007L;

        int n_man = hats.size();
        boolean[][] possible = new boolean[n_man][41];

        for (int i = 0; i < n_man; i++)
            for (int j : hats.get(i))
                possible[i][j] = true;

        long[] dp = new long[1 << n_man];
        dp[0] = 1;

        // 모든 모자 번호에 대해
        for (int i = 1; i <= 40; i++) {
            // n_man 모든 비트 부터 첫번째 비트까지 내림차순으로
            for (int j = dp.length - 1; j > 0; j--) {
                // 모든 사람 번호에 대해
                for (int k = 0; k < n_man; k++)
                    // 그사람이 해당하는 경우 && 해당 모자를 쓸 수 있을때
                    if (((j >> k) & 1) == 1 && possible[k][i]) {
                        System.out.println(
                                "모자번호:" + i + ", 사람번호:" + k
                                + " ," + j + ">" + dp[j] +
                                " ," + (j ^ (1 << k)) + ">" + dp[j ^ (1 << k)]
                        );
                        dp[j] += dp[j ^ (1 << k)];
                    }
                dp[j] %= Mod;
            }
        }
        return (int) dp[(1 << n_man) - 1];
    }
}
