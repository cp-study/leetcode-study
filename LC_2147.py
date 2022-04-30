class Solution:
    def numberOfWays(self, corridor: str) -> int:
        n = len(corridor)

        dp = [0] * 3
        dp[0] = 1

        for i in range(n):
            if corridor[i] == 'S':
                dp[0], dp[1], dp[2] = 0, dp[0] + dp[2], dp[1]
            else:
                dp[0], dp[1], dp[2] = dp[0] + dp[2], dp[1], dp[2]

        return dp[2] % (10 ** 9 + 7)
      
